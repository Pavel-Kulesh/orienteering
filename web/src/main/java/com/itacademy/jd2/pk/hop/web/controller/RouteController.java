package com.itacademy.jd2.pk.hop.web.controller;

import java.io.IOException;
import java.util.ArrayList;
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

import com.google.gson.Gson;
import com.itacademy.jd2.pk.hop.dao.api.entity.IPoint;
import com.itacademy.jd2.pk.hop.dao.api.entity.IRoute;
import com.itacademy.jd2.pk.hop.dao.api.filter.RouteFilter;
import com.itacademy.jd2.pk.hop.service.IPointService;
import com.itacademy.jd2.pk.hop.service.IRouteService;
import com.itacademy.jd2.pk.hop.web.converter.RouteFromDTOConverter;
import com.itacademy.jd2.pk.hop.web.converter.RouteToDTOConverter;
import com.itacademy.jd2.pk.hop.web.dto.PointDTO;
import com.itacademy.jd2.pk.hop.web.dto.RouteDTO;
import com.itacademy.jd2.pk.hop.web.dto.list.GridStateDTO;
import com.itacademy.jd2.pk.hop.web.tag.MyGPX;

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
	public ModelAndView handleRequest(final HttpServletRequest req,

			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		RouteFilter filter = new RouteFilter();

		prepareFilter(gridState, filter);

		final List<IRoute> entities = routeService.find(filter);
		List<RouteDTO> dtos = entities.stream().map(toDTOConverter).collect(Collectors.toList());
		gridState.setTotalCount(routeService.getCount(filter));

		final HashMap<String, Object> models = new HashMap<>();

		Integer currentCustomer = getCustomerId();
		models.put("currentCustomer", currentCustomer);

		models.put("gridItem", dtos);
		return new ModelAndView("route.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {

		final Map<String, Object> hashMap = new HashMap<>();

		RouteDTO dto = new RouteDTO();
		dto.setCustomerId(getCustomerId());
		hashMap.put("formModel", dto);

		return new ModelAndView("route.add", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@RequestParam("fileDoc") final MultipartFile fileDoc,
			@Valid @ModelAttribute("formModel") final RouteDTO formModel, final BindingResult result)
			throws IOException {

		if (fileDoc == null) {
			throw new BadCredentialsException("1000");
		}

		if (result.hasErrors()) {
			return "route.add";
		} else {

			// get String save to file and path (test program)
			// if we did/t save file to disc =>
			// =>delete attribute file and path from entity in DB

			// create new class for parse GPS=Track
			// write method to return List<IPoint>
			// getPointList(Integer customerId, fileDoc.getInputStream()){}
			// after save entity (route) to DB save List<IPoint>
			// if cant parse give exception "invalid file-gpx" and see (1)

			// https://code.i-harness.com/ru/q/6d18

			// http://qaru.site/questions/13112/calculate-distance-between-two-latitude-longitude-points-haversine-formula

			/*
			 * final String result1 = new BufferedReader(new
			 * InputStreamReader(fileDoc.getInputStream())).lines()
			 * .collect(Collectors.joining("\n")); System.out.println("result file=" +
			 * result1);
			 */

			List<IPoint> poits = MyGPX.seeTrack(fileDoc.getInputStream());
			final IRoute entity = fromDTOConverter.apply(formModel);
			entity.setFile("exemple string");
			entity.setPath("exemple path2");
			routeService.save(entity);

			for (IPoint p : poits) {
				p.setRoute(entity);
			}

			Double[] point = new Double[2];
			ArrayList<Object> points = new ArrayList<>();
			for (IPoint p : poits) {
				point[0] = p.getLatitude();
				point[1] = p.getLongitude();
				points.add(point);
			}
			String json = new Gson().toJson(points);

			pointService.saveList(poits);

			return "redirect:/route";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		pointService.delete(id);
		routeService.delete(id);
		return "redirect:/route";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IRoute dbModel = routeService.get(id);
		final RouteDTO dto = toDTOConverter.apply(dbModel);
		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		// take all point and hashMap.put("pointItems", list);

		/*
		 * List<IPoint> pointsByRoute = pointService.selectById(id);
		 * 
		 * Double[] point = new Double[2]; ArrayList<Object> points = new ArrayList<>();
		 * 
		 * int count = points.size(); Double aveLat = null; Double aveLong = null;
		 * Double sumLat = null; Double sumLong = null;
		 * 
		 * for (IPoint p : pointsByRoute) { count++; sumLat += p.getLatitude(); sumLong
		 * += p.getLongitude(); point[0] = p.getLatitude(); point[1] = p.getLongitude();
		 * points.add(point); }
		 * 
		 * if (count == 0) { throw new NullPointerException("incorrect file gpx");
		 * 
		 * } else { aveLat = sumLat / count; aveLong = sumLong / count; } String json =
		 * new Gson().toJson(points); // String json1 = new Gson().toJson(new Double);
		 * hashMap.put("json", json);
		 */

		return new ModelAndView("route.info", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {

		final RouteDTO dto = toDTOConverter.apply(routeService.get(id));

		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		return new ModelAndView("route.edit", hashMap);
	}

	@RequestMapping(value = "/points", method = RequestMethod.GET)
	public ResponseEntity<List<PointDTO>> getRoutePoints(
			@RequestParam(name = "routeId", required = true) final Integer routeId) {
		List<PointDTO> points = new ArrayList<>();
		List<IPoint> pointsFromDB = pointService.selectById(routeId);
		for (IPoint entity : pointsFromDB) {
			points.add(new PointDTO(entity.getLatitude(), entity.getLongitude()));

		}
		return new ResponseEntity<List<PointDTO>>(points, HttpStatus.OK);
	}

}
