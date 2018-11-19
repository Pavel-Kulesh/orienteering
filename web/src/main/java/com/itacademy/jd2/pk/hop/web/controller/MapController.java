package com.itacademy.jd2.pk.hop.web.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletRequest;
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
import com.itacademy.jd2.pk.hop.web.security.AuthHelper;

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
	public String save(@Valid @ModelAttribute("formModel") final MapDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			return "map.add";
		} else {

			File fileDoc = formModel.getFileDoc();

			fileDoc.getAbsolutePath();

			String filePath = fileDoc.getAbsolutePath();
			System.out.println("file path: " + filePath);
			try {
				readUsingFiles(filePath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

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

	private static void readUsingFiles(String fileName) throws IOException {
		Path path = Paths.get(fileName);
		Path pathToFile = Paths.get(fileName);
		System.out.println(pathToFile.toAbsolutePath());
		// считываем содержимое файла в массив байт
		byte[] bytes = Files.readAllBytes(path);
		// считываем содержимое файла в список строк
		List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
		for (String string : allLines) {
			System.out.println(string);

		}
	}

}
