package com.itacademy.jd2.pk.hop.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICity;
import com.itacademy.jd2.pk.hop.web.dto.CityDTO;

@Component
public class CityToDTOConverter implements Function<ICity, CityDTO> {

	@Override
	public CityDTO apply(ICity entity) {
		CityDTO dto = new CityDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		return dto;
	}

}
