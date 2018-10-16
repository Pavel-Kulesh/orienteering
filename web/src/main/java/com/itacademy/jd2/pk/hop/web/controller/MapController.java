package com.itacademy.jd2.pk.hop.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.pk.hop.dao.api.entity.IMap;
import com.itacademy.jd2.pk.hop.service.IMapService;
import com.itacademy.jd2.pk.hop.web.converter.MapToDTOConverter;
import com.itacademy.jd2.pk.hop.web.dto.MapDTO;

@Controller
@RequestMapping(value = "/maps")
public class MapController {

	@Autowired
	private IMapService mapService;
	@Autowired
	private MapToDTOConverter toDTOConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest(final HttpServletRequest req, final HttpServletResponse res) throws IOException {

		List<IMap> entities = mapService.getAll();
		List<MapDTO> dtos = entities.stream().map(toDTOConverter).collect(Collectors.toList());

		HashMap<String, Object> models = new HashMap<>();
		models.put("list", dtos);
		return new ModelAndView("map.list", models);
	}
}
