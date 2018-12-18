package com.itacademy.jd2.pk.hop.web.controller;

import java.util.Arrays;
import java.util.Date;
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
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICountry;
import com.itacademy.jd2.pk.hop.dao.api.entity.IEvent;
import com.itacademy.jd2.pk.hop.dao.api.entity.TypeEvent;
import com.itacademy.jd2.pk.hop.dao.api.filter.EventFilter;
import com.itacademy.jd2.pk.hop.service.ICountryService;
import com.itacademy.jd2.pk.hop.service.IEventService;
import com.itacademy.jd2.pk.hop.web.converter.EventFromDTOConverter;
import com.itacademy.jd2.pk.hop.web.converter.EventToDTOConverter;
import com.itacademy.jd2.pk.hop.web.dto.EventDTO;
import com.itacademy.jd2.pk.hop.web.dto.list.GridStateDTO;

@Controller
@RequestMapping(value = "/event")

public class EventController extends AbstractController<EventDTO> {

	private IEventService eventService;
	private ICountryService countryService;
	private EventToDTOConverter toDTOConverter;
	private EventFromDTOConverter fromDTOConverter;

	@Autowired
	public EventController(IEventService eventService, EventToDTOConverter toDTOConverter,
			EventFromDTOConverter fromDTOConverter, ICountryService countryService) {
		super();
		this.eventService = eventService;
		this.toDTOConverter = toDTOConverter;
		this.fromDTOConverter = fromDTOConverter;
		this.countryService = countryService;

	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest req, @RequestParam(name = "page", required = false) Integer pageNumber,
			@RequestParam(name = "sort", required = false) String sortColumn) {

		GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		EventFilter filter = new EventFilter();
		prepareFilter(gridState, filter);

		List<IEvent> entities = eventService.find(filter);
		List<EventDTO> dtos = entities.stream().map(toDTOConverter).collect(Collectors.toList());
		gridState.setTotalCount(eventService.getCount(filter));

		for (EventDTO eventDTO : dtos) {
			setStatusVisible(eventDTO);
		}

		HashMap<String, Object> models = new HashMap<>();
		models.put("gridItem", dtos);
		return new ModelAndView("event.list", models);
	}

	private void setStatusVisible(EventDTO eventDTO) {
		if (eventDTO.getCustomerId().equals(getCustomerId()) || ("ADMIN".equals(getLoginRole()))) {
			eventDTO.setStatusVisible(true);
		}

	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		Map<String, Object> hashMap = new HashMap<>();
		Integer customerId = getCustomerId();
		EventDTO dto = new EventDTO();
		dto.setCustomerId(customerId);
		hashMap.put("formModel", dto);

		loadComboboxesModels(hashMap);
		return new ModelAndView("event.add", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") EventDTO formModel, BindingResult result) {
		if (result.hasErrors()) {
			return "event.edit";
		} else {
			IEvent entity = fromDTOConverter.apply(formModel);
			eventService.save(entity);
			return "redirect:/event";
		}
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) Integer id) {
		String loginRole = getLoginRole();
		if (loginRole.equals("ADMIN")) {
			eventService.delete(id);
		} else {
			IEvent entityDB = eventService.get(id);
			if (entityDB.getCustomer().getId().equals(getCustomerId())) {
				eventService.delete(id);
				return "redirect:/event";
			}
			throw new IllegalArgumentException("cannot delete");
		}

		return "redirect:/event";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) Integer id) {
		IEvent dbModel = eventService.get(id);
		EventDTO dto = toDTOConverter.apply(dbModel);
		HashMap<String, Object> hashMap = new HashMap<>();

		setStatusVisible(dto);
		hashMap.put("formModel", dto);
		checkDate(id, dto, hashMap);

		return new ModelAndView("event.info", hashMap);
	}

	private void checkDate(Integer id, EventDTO dto, HashMap<String, Object> hashMap) {
		Date date = new Date();
		boolean regPossibility = true;
		if (date.after(dto.getDate())) {
			regPossibility = false;
		}
		hashMap.put("regPossibility", regPossibility);
		Integer customerId = getCustomerId();
		if (customerId != null) {
			setStatusRegToEvent(id, hashMap);
		}
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) Integer id, HttpServletRequest req) {
		EventDTO dto = toDTOConverter.apply(eventService.get(id));

		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadComboboxesModels(hashMap);
		String url = req.getHeader("referer");
		hashMap.put("url", url);
		return new ModelAndView("event.edit", hashMap);
	}

	@RequestMapping(value = "/registrationCustomerToEvent/{id}", method = RequestMethod.GET)
	public ModelAndView addCustomerToEvent(@PathVariable(name = "id", required = true) Integer id) {

		eventService.addCustomerToEvent(getCustomerId(), id);
		IEvent dbModel = eventService.get(id);
		EventDTO dto = toDTOConverter.apply(dbModel);
		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		setStatusRegToEvent(id, hashMap);
		checkDate(id, dto, hashMap);
		return new ModelAndView("event.info", hashMap);

	}

	@RequestMapping(value = "/deleteCustomerFromEvent/{id}", method = RequestMethod.GET)
	public ModelAndView deleteCustomerFromEvent(@PathVariable(name = "id", required = true) Integer id) {

		eventService.deleteCustomerFromEvent(getCustomerId(), id);
		IEvent dbModel = eventService.get(id);
		EventDTO dto = toDTOConverter.apply(dbModel);
		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		setStatusRegToEvent(id, hashMap);
		checkDate(id, dto, hashMap);
		return new ModelAndView("event.info", hashMap);
	}

	private void setStatusRegToEvent(Integer id, HashMap<String, Object> hashMap) {
		boolean statusRegOnEvent = eventService.checkExistCustomerInEvent(getCustomerId(), id);
		hashMap.put("registerToEvent", !statusRegOnEvent);
		hashMap.put("deleteFromEvent", statusRegOnEvent);
	}

	private void loadComboboxesModels(Map<String, Object> hashMap) {

		List<TypeEvent> eventTypesList = Arrays.asList(TypeEvent.values());
		Map<String, String> eventTypesMap = eventTypesList.stream()
				.collect(Collectors.toMap(TypeEvent::name, TypeEvent::name));

		hashMap.put("typeChoices", eventTypesMap);
		List<ICountry> countries = countryService.getAll();
		Map<Integer, String> countriesMap = countries.stream()
				.collect(Collectors.toMap(ICountry::getId, ICountry::getName));

		hashMap.put("countryChoices", countriesMap);

	}

}
