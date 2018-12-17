package com.itacademy.jd2.pk.hop.web.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);

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
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final EventFilter filter = new EventFilter();
		prepareFilter(gridState, filter);

		final List<IEvent> entities = eventService.find(filter);
		List<EventDTO> dtos = entities.stream().map(toDTOConverter).collect(Collectors.toList());
		gridState.setTotalCount(eventService.getCount(filter));

		for (EventDTO eventDTO : dtos) {
			setStatusVisible(eventDTO);
		}

		final HashMap<String, Object> models = new HashMap<>();
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
		final Map<String, Object> hashMap = new HashMap<>();
		Integer customerId = getCustomerId();
		LOGGER.info("user id" + customerId);
		EventDTO dto = new EventDTO();
		dto.setCustomerId(customerId);
		hashMap.put("formModel", dto);

		loadComboboxesModels(hashMap);
		return new ModelAndView("event.add", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") final EventDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			return "event.edit";
		} else {
			final IEvent entity = fromDTOConverter.apply(formModel);
			eventService.save(entity);
			return "redirect:/event";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		String loginRole = getLoginRole();
		if (loginRole.equals("ADMIN")) {
			// delete all participants at event list
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
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IEvent dbModel = eventService.get(id);
		final EventDTO dto = toDTOConverter.apply(dbModel);
		final HashMap<String, Object> hashMap = new HashMap<>();
		
		setStatusVisible(dto);
		hashMap.put("formModel", dto);
		checkDate(id, dto, hashMap);

		return new ModelAndView("event.info", hashMap);
	}

	private void checkDate(final Integer id, final EventDTO dto, final HashMap<String, Object> hashMap) {
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

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id, HttpServletRequest req) {
		final EventDTO dto = toDTOConverter.apply(eventService.get(id));

		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadComboboxesModels(hashMap);
		String url = req.getHeader("referer");
		hashMap.put("url",url);
		return new ModelAndView("event.edit", hashMap);
	}

	@RequestMapping(value = "/registrationCustomerToEvent/{id}", method = RequestMethod.GET)
	public ModelAndView addCustomerToEvent(@PathVariable(name = "id", required = true) final Integer id) {

		eventService.addCustomerToEvent(getCustomerId(), id);
		final IEvent dbModel = eventService.get(id);
		final EventDTO dto = toDTOConverter.apply(dbModel);
		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		setStatusRegToEvent(id, hashMap);
		checkDate(id, dto, hashMap);
		return new ModelAndView("event.info", hashMap);

	}

	@RequestMapping(value = "/deleteCustomerFromEvent/{id}", method = RequestMethod.GET)
	public ModelAndView deleteCustomerFromEvent(@PathVariable(name = "id", required = true) final Integer id) {

		eventService.deleteCustomerFromEvent(getCustomerId(), id);
		final IEvent dbModel = eventService.get(id);
		final EventDTO dto = toDTOConverter.apply(dbModel);
		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		setStatusRegToEvent(id, hashMap);
		checkDate(id, dto, hashMap);
		return new ModelAndView("event.info", hashMap);
	}

	private void setStatusRegToEvent(final Integer id, final HashMap<String, Object> hashMap) {
		boolean statusRegOnEvent = eventService.checkExistCustomerInEvent(getCustomerId(), id);
		hashMap.put("registerToEvent", !statusRegOnEvent);
		hashMap.put("deleteFromEvent", statusRegOnEvent);
	}

	private void loadComboboxesModels(final Map<String, Object> hashMap) {

		final List<TypeEvent> eventTypesList = Arrays.asList(TypeEvent.values());
		final Map<String, String> eventTypesMap = eventTypesList.stream()
				.collect(Collectors.toMap(TypeEvent::name, TypeEvent::name));

		hashMap.put("typeChoices", eventTypesMap);

		final List<ICountry> countries = countryService.getAll();

		final Map<Integer, String> countriesMap = countries.stream()
				.collect(Collectors.toMap(ICountry::getId, ICountry::getName));

		hashMap.put("countryChoices", countriesMap);

	}

}
