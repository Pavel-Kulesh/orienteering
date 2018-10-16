package com.itacademy.jd2.pk.hop.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.pk.hop.dao.api.entity.IEvent;
import com.itacademy.jd2.pk.hop.service.IEventService;
import com.itacademy.jd2.pk.hop.web.dto.EventDTO;

@Component
public class EventFromDTOConverter implements Function<EventDTO, IEvent> {

	@Autowired
	private IEventService eventServise;

	@Override
	public IEvent apply(EventDTO dto) {
		IEvent entity = eventServise.createEntity();

		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setCreatorId(dto.getCreatorId());
		entity.setDate(dto.getDate());
		entity.setCountryId(dto.getCountryId());
		entity.setType(dto.getType());
		entity.setInfo(dto.getInfo());
		entity.setLatitude(dto.getLatitude());
		entity.setLongitude(dto.getLongitude());

		return entity;
	}

}
