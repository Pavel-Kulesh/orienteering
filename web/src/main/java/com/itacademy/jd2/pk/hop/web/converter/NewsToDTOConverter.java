package com.itacademy.jd2.pk.hop.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.pk.hop.dao.api.entity.INews;
import com.itacademy.jd2.pk.hop.web.dto.NewsDTO;

@Component
public class NewsToDTOConverter implements Function<INews, NewsDTO> {

	@Override
	public NewsDTO apply(final INews entity) {
		final NewsDTO dto = new NewsDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		return dto;
	}

}
