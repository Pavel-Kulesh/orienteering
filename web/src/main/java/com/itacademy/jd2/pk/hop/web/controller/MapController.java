package com.itacademy.jd2.pk.hop.web.controller;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.pk.hop.dao.api.entity.IMap;
import com.itacademy.jd2.pk.hop.dao.api.entity.IPoint;
import com.itacademy.jd2.pk.hop.dao.api.entity.IRoute;
import com.itacademy.jd2.pk.hop.dao.api.filter.MapFilter;
import com.itacademy.jd2.pk.hop.service.IMapService;
import com.itacademy.jd2.pk.hop.service.IRouteService;
import com.itacademy.jd2.pk.hop.web.converter.MapFromDTOConverter;
import com.itacademy.jd2.pk.hop.web.converter.MapToDTOConverter;
import com.itacademy.jd2.pk.hop.web.dto.IdHolder;
import com.itacademy.jd2.pk.hop.web.dto.MapDTO;
import com.itacademy.jd2.pk.hop.web.dto.NewsDTO;
import com.itacademy.jd2.pk.hop.web.dto.PointDTO;
import com.itacademy.jd2.pk.hop.web.dto.list.GridStateDTO;

@Controller
@RequestMapping(value = "/map")
public class MapController extends AbstractController<MapDTO> {

	private IMapService mapService;
	private MapToDTOConverter toDTOConverter;
	private MapFromDTOConverter fromDTOConverter;
	private IRouteService routeService;

	@Autowired
	public MapController(IMapService mapService, MapToDTOConverter toDTOConverter, MapFromDTOConverter fromDTOConverter,
			IRouteService routeService) {
		super();
		this.mapService = mapService;
		this.toDTOConverter = toDTOConverter;
		this.fromDTOConverter = fromDTOConverter;
		this.routeService = routeService;

	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest(final HttpServletRequest req,

			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final MapFilter filter = new MapFilter();
		prepareFilter(gridState, filter);

		final List<IMap> entities = mapService.find(filter);
		List<MapDTO> dtos = entities.stream().map(toDTOConverter).collect(Collectors.toList());
		gridState.setTotalCount(mapService.getCount(filter));

		String currentLoginRole = getLoginRole();
		Integer currentCustomerId = getCustomerId();
		for (MapDTO mapDTO : dtos) {
			if (mapDTO.getCustomerId().equals(currentCustomerId) || ("ADMIN".equals(currentLoginRole))) {
				mapDTO.setStatusVisible(true);
			}
		}

		final HashMap<String, Object> models = new HashMap<>();
		models.put("gridItem", dtos);
		return new ModelAndView("map.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();

		MapDTO dto = new MapDTO();
		dto.setCustomerId(getCustomerId());
		hashMap.put("formModel", dto);

		return new ModelAndView("map.add", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@RequestParam("fileDoc") final MultipartFile fileDoc,
			@Valid @ModelAttribute("formModel") final MapDTO formModel, final BindingResult result) throws IOException {
		if (result.hasErrors()) {
			return "map.add";
		} else {
			
			final IMap entity = fromDTOConverter.apply(formModel);
			entity.setImage(fileDoc.getBytes());
			entity.setFile("random for test");
			mapService.save(entity);

			return "redirect:/map";
		}
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(@Valid @ModelAttribute("formModel") final MapDTO formModel, final BindingResult result)
			throws IOException {
		if (result.hasErrors()) {
			return "map.edit";
		} else {
			final IMap entity = fromDTOConverter.apply(formModel);
			mapService.save(entity);
			
			return "redirect:/map";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		mapService.delete(id);
		return "redirect:/map";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IMap dbModel = mapService.get(id);
		final MapDTO dto = toDTOConverter.apply(dbModel);
		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("idHolder", new IdHolder());

		loadComboboxesModels(hashMap, id);
		return new ModelAndView("map.info", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final MapDTO dto = toDTOConverter.apply(mapService.get(id));

		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		return new ModelAndView("map.edit", hashMap);
	}

	@RequestMapping(value = "/addRouteToMap/{mapId}", method = RequestMethod.POST)
	public ModelAndView addRouteToMap(@PathVariable(name = "mapId", required = true) final Integer mapId,
			@Valid @ModelAttribute("idHolder") final IdHolder idHolder) {

		final IMap dbModel = mapService.get(mapId);
		final MapDTO dto = toDTOConverter.apply(dbModel);
		final HashMap<String, Object> hashMap = new HashMap<>();

		mapService.addRouteToMap(mapId, idHolder.getId());

		hashMap.put("formModel", dto);

		loadComboboxesModels(hashMap, mapId);

		return new ModelAndView("map.info", hashMap);

	}

	@RequestMapping(value = "/deleteRouteFromMap/{id}", method = RequestMethod.GET)
	public ModelAndView deleteRouteFromMap(@PathVariable(name = "id", required = true) final Integer mapId,
			@Valid @ModelAttribute("idHolder") final IdHolder idHolder) {

		final IMap dbModel = mapService.get(mapId);
		final MapDTO dto = toDTOConverter.apply(dbModel);
		final HashMap<String, Object> hashMap = new HashMap<>();

		mapService.deleteRouteFromMap(mapId, idHolder.getId());

		hashMap.put("formModel", dto);

		loadComboboxesModels(hashMap, mapId);

		return new ModelAndView("map.info", hashMap);

	}

	private void loadComboboxesModels(final Map<String, Object> hashMap, Integer mapId) {
		List<IRoute> customerRoutes1 = new ArrayList<>();
		List<IRoute> routesOnMapByCustomer1 = new ArrayList<>();

		List<IRoute> routesOnMap1 = mapService.getRoutesOnMap(mapId);
		Map<Integer, String> routesOnMap = routesOnMap1.stream()
				.collect(Collectors.toMap(IRoute::getId, IRoute::getName));
		hashMap.put("mapRoutes", routesOnMap);

		if (getCustomerId() != null) {
			if (getLoginRole().equals("ADMIN")) {
				hashMap.put("myRoutesOnMap", routesOnMap);
			} else {
				routesOnMapByCustomer1 = mapService.getRoutesOnMapByCustomer(mapId, getCustomerId());
				Map<Integer, String> routesOnMapByCustomer = routesOnMapByCustomer1.stream()
						.collect(Collectors.toMap(IRoute::getId, IRoute::getName));
				hashMap.put("myRoutesOnMap", routesOnMapByCustomer);
			}
		}

		if ("ADMIN".equals(getLoginRole())) {
			customerRoutes1 = routeService.getAll();
		} else {
			customerRoutes1 = routeService.getCustomerRoutes(getCustomerId());
		}

		Map<Integer, String> customerRoutes = customerRoutes1.stream()
				.collect(Collectors.toMap(IRoute::getId, IRoute::getName));

		hashMap.put("myRoutes", customerRoutes);

	}

	/*
	 * @RequestMapping(method = RequestMethod.GET, value = "/image/{mapId}") public
	 * void getImageAsByteArray(HttpServletResponse response,
	 * 
	 * @PathVariable(name = "mapId", required = true) final Integer mapId) throws
	 * IOException { IMap entity = mapService.get(mapId);
	 * response.setContentType(MediaType.IMAGE_JPEG_VALUE);
	 * 
	 * byte[] buf = entity.getImage();
	 * 
	 * String encoded = Base64.getEncoder().encodeToString(buf);
	 * System.out.println(encoded);
	 * 
	 * ByteArrayInputStream input = new ByteArrayInputStream(buf);
	 * IOUtils.copy(input, response.getOutputStream()); }
	 */

	@RequestMapping(method = RequestMethod.GET, value = "/image/{mapId}")
	public ResponseEntity<String> getString(@PathVariable(name = "mapId", required = true) final Integer mapId)
			throws IOException {
		IMap entity = mapService.get(mapId);

		byte[] buf = entity.getImage();

		String encoded = Base64.getEncoder().encodeToString(buf);
		System.out.println(encoded);

		return new ResponseEntity<String>(encoded, HttpStatus.OK);
	}

}
