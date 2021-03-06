package com.itacademy.jd2.pk.hop.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.pk.hop.dao.api.entity.IPoint;
import com.itacademy.jd2.pk.hop.dao.api.entity.IRoute;
import com.itacademy.jd2.pk.hop.dao.api.entity.TypeTrack;
import com.itacademy.jd2.pk.hop.dao.api.filter.RouteFilter;
import com.itacademy.jd2.pk.hop.service.IPointService;
import com.itacademy.jd2.pk.hop.service.IRouteService;
import com.itacademy.jd2.pk.hop.web.converter.RouteFromDTOConverter;
import com.itacademy.jd2.pk.hop.web.converter.RouteToDTOConverter;
import com.itacademy.jd2.pk.hop.web.dto.PointDTO;
import com.itacademy.jd2.pk.hop.web.dto.RouteDTO;
import com.itacademy.jd2.pk.hop.web.dto.RouteDataResponse;
import com.itacademy.jd2.pk.hop.web.dto.SpeedDTO;
import com.itacademy.jd2.pk.hop.web.dto.list.GridStateDTO;
import com.itacademy.jd2.pk.hop.web.gpx.MyGPX;

@Controller
@RequestMapping(value = "/route")
public class RouteController extends AbstractController<RouteDTO> {

	private IRouteService routeService;
	private RouteToDTOConverter toDTOConverter;
	private RouteFromDTOConverter fromDTOConverter;
	private IPointService pointService;

	@Autowired
	public RouteController(IRouteService routeService, RouteToDTOConverter toDTOConverter,
			RouteFromDTOConverter fromDTOConverter, IPointService pointService) {
		super();
		this.routeService = routeService;
		this.toDTOConverter = toDTOConverter;
		this.fromDTOConverter = fromDTOConverter;
		this.pointService = pointService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest(HttpServletRequest req,

			@RequestParam(name = "page", required = false) Integer pageNumber,
			@RequestParam(name = "sort", required = false) String sortColumn) {

		GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		RouteFilter filter = new RouteFilter();
		filter.setCustomerId(getCustomerId());
		filter.setUserRole(getLoginRole());
		prepareFilter(gridState, filter);

		List<IRoute> entities = routeService.find(filter);
		List<RouteDTO> dtos = entities.stream().map(toDTOConverter).collect(Collectors.toList());
		gridState.setTotalCount(routeService.getCount(filter));

		HashMap<String, Object> models = new HashMap<>();

		models.put("gridItem", dtos);
		return new ModelAndView("route.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {

		Map<String, Object> hashMap = new HashMap<>();

		RouteDTO dto = new RouteDTO();
		dto.setCustomerId(getCustomerId());
		hashMap.put("formModel", dto);

		loadComboboxesModels(hashMap);
		return new ModelAndView("route.add", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@RequestParam("fileDoc") MultipartFile fileDoc,
			@Valid @ModelAttribute("formModel") RouteDTO formModel, BindingResult result) throws IOException {

		if (fileDoc == null) {
			throw new BadCredentialsException("1000");
		}
		if (result.hasErrors()) {
			return "route.add";
		} else {

			List<IPoint> poits = MyGPX.seeTrack(fileDoc.getInputStream());
			IRoute entity = fromDTOConverter.apply(formModel);
			routeService.save(entity);

			for (IPoint p : poits) {
				p.setRoute(entity);
			}
			pointService.saveList(poits);
			return "redirect:/route";
		}
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(@Valid @ModelAttribute("formModel") RouteDTO formModel, BindingResult result)
			throws IOException {

		if (result.hasErrors()) {
			return "route.edit";
		} else {
			IRoute entity = fromDTOConverter.apply(formModel);
			routeService.save(entity);
			return "redirect:/route";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) Integer id) {
		routeService.delete(id);
		return "redirect:/route";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) Integer id) {
		IRoute dbModel = routeService.get(id);
		RouteDTO dto = toDTOConverter.apply(dbModel);
		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		return new ModelAndView("route.info", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) Integer id) {

		RouteDTO dto = toDTOConverter.apply(routeService.get(id));

		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadComboboxesModels(hashMap);
		return new ModelAndView("route.edit", hashMap);
	}

	@RequestMapping(value = "/points", method = RequestMethod.GET)
	public ResponseEntity<RouteDataResponse> getRoutePoints(
			@RequestParam(name = "routeId", required = true) Integer routeId) {
		List<PointDTO> points = new ArrayList<>();
		List<IPoint> pointsFromDB = pointService.selectByRouteId(routeId);
		for (IPoint entity : pointsFromDB) {
			points.add(new PointDTO(entity.getLatitude(), entity.getLongitude()));

		}

		RouteDataResponse routeDataResponse = new RouteDataResponse();
		IRoute route = routeService.get(routeId);
		routeDataResponse.setName(route.getName());
		routeDataResponse.setPoints(points);
		return new ResponseEntity<RouteDataResponse>(routeDataResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "/speed", method = RequestMethod.GET)
	public ResponseEntity<List<SpeedDTO>> getPointsInfo(
			@RequestParam(name = "routeId", required = true) Integer routeId) {
		List<IPoint> pointsFromDB = pointService.selectByRouteId(routeId);

		List<SpeedDTO> speedInterval = getSpeedInterval(pointsFromDB);

		return new ResponseEntity<List<SpeedDTO>>(speedInterval, HttpStatus.OK);
	}

	private List<SpeedDTO> getSpeedInterval(List<IPoint> points) {
		List<SpeedDTO> intervals = new ArrayList<>();
		IPoint startPoint = points.get(0);
		for (IPoint point : points) {
			double distance = getDistanceBetween(startPoint.getLatitude(), startPoint.getLongitude(),
					point.getLatitude(), point.getLongitude());
			intervals.add(new SpeedDTO(distance, point.getDiffTime()));
			startPoint = point;
		}
		return intervals;
	}

	private void loadComboboxesModels(Map<String, Object> hashMap) {

		List<TypeTrack> eventTypesList = Arrays.asList(TypeTrack.values());
		Map<String, String> eventTypesMap = eventTypesList.stream()
				.collect(Collectors.toMap(TypeTrack::name, TypeTrack::name));

		hashMap.put("wayChoices", eventTypesMap);
	}

	public double getDistanceBetween(double lat1, double lon1, double lat2, double lon2) {
		double dLat = toRadians(lat2 - lat1);
		double dLon = toRadians(lon2 - lon1);

		double a = (Math.sin(dLat / 2) * Math.sin(dLat / 2))
				+ (Math.cos(toRadians(lat1)) * Math.cos(toRadians(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2));
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		// double d = EARTH_RADIUS * c;
		double d = 6371 * c;
		return d;
	}

	public double toRadians(double degrees) {
		return degrees * (Math.PI / 180);
	}

}
