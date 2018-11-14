package com.itacademy.jd2.pk.hop.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.pk.hop.dao.api.entity.IEvent;
import com.itacademy.jd2.pk.hop.web.dto.EventDTO;

@Component
public class EventListToDTOConverter implements Function<IEvent, EventDTO> {

	@Override
	public EventDTO apply(IEvent entity) {
		EventDTO dto = new EventDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setDate(entity.getDate());
		dto.setType(entity.getType());
		return dto;
	}

}