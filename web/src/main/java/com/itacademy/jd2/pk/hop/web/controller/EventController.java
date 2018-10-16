package com.itacademy.jd2.pk.hop.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.pk.hop.dao.api.entity.IEvent;
import com.itacademy.jd2.pk.hop.service.IEventService;
import com.itacademy.jd2.pk.hop.web.converter.EventToDTOConverter;
import com.itacademy.jd2.pk.hop.web.dto.EventDTO;

@Controller
@RequestMapping(value = "/events")

public class EventController {
	@Autowired
	private IEventService eventService;

	@Autowired
	private EventToDTOConverter toDTOConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest(final HttpServletRequest req, final HttpServletResponse res) throws IOException {
		List<IEvent> entities = eventService.getAll();

		List<EventDTO> dtos = entities.stream().map(toDTOConverter).collect(Collectors.toList());
		Map<String, Object> models = new HashMap<>();
		models.put("list", dtos);
		return new ModelAndView("event.list", models);
	}

}
