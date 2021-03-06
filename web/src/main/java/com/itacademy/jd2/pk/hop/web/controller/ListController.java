package com.itacademy.jd2.pk.hop.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICustomer;
import com.itacademy.jd2.pk.hop.dao.api.entity.IEvent;
import com.itacademy.jd2.pk.hop.service.ICustomerService;
import com.itacademy.jd2.pk.hop.service.IEventService;
import com.itacademy.jd2.pk.hop.web.converter.CustomersListToDTOConverter;
import com.itacademy.jd2.pk.hop.web.converter.EventListToDTOConverter;
import com.itacademy.jd2.pk.hop.web.dto.CustomerDTO;
import com.itacademy.jd2.pk.hop.web.dto.EventDTO;

@Controller
@RequestMapping(value = "/list")
public class ListController {
	private ICustomerService customerService;
	private CustomersListToDTOConverter customerToDTOConverter;
	private EventListToDTOConverter eventToDTOConverter;
	private IEventService eventService;

	@Autowired
	public ListController(ICustomerService customerService, CustomersListToDTOConverter customerToDTOConverter,
			EventListToDTOConverter eventToDTOConverter, IEventService eventService) {
		super();
		this.customerService = customerService;
		this.customerToDTOConverter = customerToDTOConverter;
		this.eventToDTOConverter = eventToDTOConverter;
		this.eventService = eventService;
	}

	@RequestMapping(value = "/event/{id}", method = RequestMethod.GET)
	public ModelAndView showUsers(@PathVariable(name = "id", required = true) Integer id, HttpServletRequest req) {

		List<ICustomer> entities = customerService.getCustomersByEvent(id);

		List<CustomerDTO> dtos = entities.stream().map(customerToDTOConverter).collect(Collectors.toList());

		HashMap<String, Object> models = new HashMap<>();

		models.put("gridItem", dtos);

		String url = req.getHeader("referer");
		models.put("url", url);
		return new ModelAndView("user2event", models);

	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ModelAndView showEvents(@PathVariable(name = "id", required = true) Integer id, HttpServletRequest req) {

		List<IEvent> entities = eventService.getEventsByCustomer(id);

		List<EventDTO> dtos = entities.stream().map(eventToDTOConverter).collect(Collectors.toList());

		HashMap<String, Object> models = new HashMap<>();
		models.put("gridItem", dtos);

		String url = req.getHeader("referer");
		models.put("url", url);
		return new ModelAndView("event2user", models);

	}
}
