package com.itacademy.jd2.pk.hop.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICountry;
import com.itacademy.jd2.pk.hop.web.dto.CountryDTO;

@Component
public class CountryToDTOConverter implements Function<ICountry, CountryDTO> {

	@Override
	public CountryDTO apply(ICountry entity) {

		CountryDTO dto = new CountryDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		return dto;
	}

}
