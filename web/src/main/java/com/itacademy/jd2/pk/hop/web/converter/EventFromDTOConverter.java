package com.itacademy.jd2.pk.hop.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICountry;
import com.itacademy.jd2.pk.hop.dao.api.entity.ICustomer;
import com.itacademy.jd2.pk.hop.dao.api.entity.IEvent;
import com.itacademy.jd2.pk.hop.service.ICountryService;
import com.itacademy.jd2.pk.hop.service.ICustomerService;
import com.itacademy.jd2.pk.hop.service.IEventService;
import com.itacademy.jd2.pk.hop.web.dto.EventDTO;

@Component
public class EventFromDTOConverter implements Function<EventDTO, IEvent> {

	private IEventService eventServise;
	private ICountryService countryService;
	private ICustomerService customerService;

	@Autowired

	public EventFromDTOConverter(IEventService eventServise, ICountryService countryService,
			ICustomerService customerService) {
		super();
		this.eventServise = eventServise;
		this.countryService = countryService;
		this.customerService = customerService;
	}

	@Override
	public IEvent apply(EventDTO dto) {
		IEvent entity = eventServise.createEntity();

		entity.setId(dto.getId());
		entity.setName(dto.getName());
		ICustomer customer = customerService.get(dto.getCustomerId());
		entity.setCustomer(customer);
		entity.setDate(dto.getDate());
		ICountry country = countryService.get(dto.getCountryId());
		entity.setCountry(country);
		entity.setType(dto.getType());
		entity.setInfo(dto.getInfo());
		entity.setLatitude(dto.getLatitude());
		entity.setLongitude(dto.getLongitude());
		entity.setVersion(dto.getVersion());
		return entity;
	}

}
