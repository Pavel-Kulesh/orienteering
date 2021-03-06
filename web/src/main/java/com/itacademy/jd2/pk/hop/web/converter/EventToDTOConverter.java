package com.itacademy.jd2.pk.hop.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.pk.hop.dao.api.entity.IEvent;
import com.itacademy.jd2.pk.hop.web.dto.EventDTO;

@Component
public class EventToDTOConverter implements Function<IEvent, EventDTO> {

	@Override
	public EventDTO apply(IEvent entity) {
		EventDTO dto = new EventDTO();
		dto.setId(entity.getId());
		dto.setInfo(entity.getInfo());
		dto.setName(entity.getName());
		dto.setCustomerId(entity.getCustomer().getId());
		dto.setDate(entity.getDate());
		dto.setCountryId(entity.getCountry().getId());
		dto.setType(entity.getType());
		dto.setLatitude(entity.getLatitude());
		dto.setLongitude(entity.getLongitude());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		dto.setCountryName(entity.getCountry().getName());
		dto.setVersion(entity.getVersion());
		return dto;
	}

}
