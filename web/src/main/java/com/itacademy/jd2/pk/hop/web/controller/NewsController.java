package com.itacademy.jd2.pk.hop.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.pk.hop.dao.api.entity.INews;
import com.itacademy.jd2.pk.hop.service.INewsServise;
import com.itacademy.jd2.pk.hop.web.converter.NewsToDTOConverter;
import com.itacademy.jd2.pk.hop.web.dto.NewsDTO;

@Controller
@RequestMapping(value = "/news")
public class NewsController {

	@Autowired
	private INewsServise newsService;
	@Autowired
	private NewsToDTOConverter toDtoConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest(final HttpServletRequest req, final HttpServletResponse res) throws IOException {
		final List<INews> entities = newsService.getAll();
		List<NewsDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final HashMap<String, Object> models = new HashMap<>();
		models.put("list", dtos);
		return new ModelAndView("news.list", models);
	}

}
