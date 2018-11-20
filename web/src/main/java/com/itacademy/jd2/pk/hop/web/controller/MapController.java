package com.itacademy.jd2.pk.hop.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.itacademy.jd2.pk.hop.dao.api.filter.MapFilter;
import com.itacademy.jd2.pk.hop.service.IMapService;
import com.itacademy.jd2.pk.hop.web.converter.MapFromDTOConverter;
import com.itacademy.jd2.pk.hop.web.converter.MapToDTOConverter;
import com.itacademy.jd2.pk.hop.web.dto.MapDTO;
import com.itacademy.jd2.pk.hop.web.dto.list.GridStateDTO;

@Controller
@RequestMapping(value = "/map")
public class MapController extends AbstractController<MapDTO> {

	private IMapService mapService;
	private MapToDTOConverter toDTOConverter;
	private MapFromDTOConverter fromDTOConverter;

	@Autowired
	public MapController(IMapService mapService, MapToDTOConverter toDTOConverter,
			MapFromDTOConverter fromDTOConverter) {
		super();
		this.mapService = mapService;
		this.toDTOConverter = toDTOConverter;
		this.fromDTOConverter = fromDTOConverter;
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

			final String result1 = new BufferedReader(new InputStreamReader(fileDoc.getInputStream())).lines()
					.collect(Collectors.joining("\n"));
			System.out.println("result file=" + result1);

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
		return new ModelAndView("map.info", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final MapDTO dto = toDTOConverter.apply(mapService.get(id));

		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		return new ModelAndView("map.edit", hashMap);
	}

}
