package com.itacademy.jd2.pk.hop.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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
import com.itacademy.jd2.pk.hop.web.converter.CustomerToDTOConverter;
import com.itacademy.jd2.pk.hop.web.converter.EventToDTOConverter;
import com.itacademy.jd2.pk.hop.web.dto.CustomerDTO;
import com.itacademy.jd2.pk.hop.web.dto.EventDTO;

@Controller
@RequestMapping(value = "/list")
public class ListController {
	private ICustomerService customerService;
	private CustomerToDTOConverter customerToDTOConverter;
	private EventToDTOConverter eventToDTOConverter;
	private IEventService eventService;

	@Autowired
	public ListController(ICustomerService customerService, CustomerToDTOConverter customerToDTOConverter,
			EventToDTOConverter eventToDTOConverter, IEventService eventService) {
		super();
		this.customerService = customerService;
		this.customerToDTOConverter = customerToDTOConverter;
		this.eventToDTOConverter = eventToDTOConverter;
		this.eventService = eventService;
	}

	@RequestMapping(value = "/event/{id}", method = RequestMethod.GET)
	public ModelAndView showUsers(@PathVariable(name = "id", required = true) final Integer id) {

		final List<ICustomer> entities = customerService.getCustomersByEvent(id);

		List<CustomerDTO> dtos = entities.stream().map(customerToDTOConverter).collect(Collectors.toList());

		final HashMap<String, Object> models = new HashMap<>();
		models.put("gridItem", dtos);
		return new ModelAndView("user2event", models);

	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ModelAndView showEvents(@PathVariable(name = "id", required = true) final Integer id) {

		final List<IEvent> entities = eventService.getEventsByCustomer(id);

		List<EventDTO> dtos = entities.stream().map(eventToDTOConverter).collect(Collectors.toList());

		final HashMap<String, Object> models = new HashMap<>();
		models.put("gridItem", dtos);
		return new ModelAndView("event2user", models);

	}
}
