package com.itacademy.jd2.pk.hop.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.pk.hop.dao.api.entity.INews;
import com.itacademy.jd2.pk.hop.service.INewsServise;
import com.itacademy.jd2.pk.hop.web.dto.NewsDTO;

@Component
public class NewsFromDTOConverter implements Function<NewsDTO, INews> {

	@Autowired
	private INewsServise newsService;

	@Override
	public INews apply(final NewsDTO dto) {
		final INews entity = newsService.createEntity();
		entity.setId(dto.getId());
		entity.setInfo(dto.getInfo());
		entity.setName(dto.getName());
		return entity;
	}
}
