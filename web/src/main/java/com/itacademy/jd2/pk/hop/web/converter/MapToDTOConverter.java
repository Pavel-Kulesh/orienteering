package com.itacademy.jd2.pk.hop.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.pk.hop.dao.api.entity.IMap;
import com.itacademy.jd2.pk.hop.web.dto.MapDTO;

@Component
public class MapToDTOConverter implements Function<IMap, MapDTO> {

	@Override
	public MapDTO apply(IMap entity) {

		MapDTO dto = new MapDTO();

		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setCustomerId(entity.getCustomer().getId());
		dto.setLatitude1(entity.getLatitude1());
		dto.setLatitude2(entity.getLatitude2());
		dto.setLongitude1(entity.getLongitude1());
		dto.setLongitude2(entity.getLongitude2());
		dto.setImage(entity.getImage());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());

		return dto;
	}

}
