package com.itacademy.jd2.pk.hop.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.pk.hop.dao.api.entity.INews;
import com.itacademy.jd2.pk.hop.dao.api.filter.NewsFilter;
import com.itacademy.jd2.pk.hop.service.INewsServise;
import com.itacademy.jd2.pk.hop.web.converter.NewsFromDTOConverter;
import com.itacademy.jd2.pk.hop.web.converter.NewsToDTOConverter;
import com.itacademy.jd2.pk.hop.web.dto.NewsDTO;
import com.itacademy.jd2.pk.hop.web.dto.list.GridStateDTO;

@Controller
@RequestMapping(value = "/news")
public class NewsController extends AbstractController<NewsDTO> {

	private INewsServise newsService;

	private NewsToDTOConverter toDTOConverter;

	private NewsFromDTOConverter fromDTOConverter;

	@Autowired
	public NewsController(INewsServise newsService, NewsToDTOConverter toDTOConverter,
			NewsFromDTOConverter fromDTOConverter) {
		super();
		this.newsService = newsService;
		this.toDTOConverter = toDTOConverter;
		this.fromDTOConverter = fromDTOConverter;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final NewsFilter filter = new NewsFilter();
		prepareFilter(gridState, filter);

		final List<INews> entities = newsService.find(filter);
		List<NewsDTO> dtos = entities.stream().map(toDTOConverter).collect(Collectors.toList());
		gridState.setTotalCount(newsService.getCount(filter));

		final HashMap<String, Object> models = new HashMap<>();
		models.put("gridItem", dtos);
		return new ModelAndView("news.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final INews newEntity = newsService.createEntity();
		NewsDTO dto = toDTOConverter.apply(newEntity);
		hashMap.put("formModel", dto);

		return new ModelAndView("news.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") final NewsDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			return "news.edit";
		} else {
			final INews entity = fromDTOConverter.apply(formModel);
			newsService.save(entity);
			return "redirect:/news";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		newsService.delete(id);
		return "redirect:/news";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final INews dbModel = newsService.get(id);
		final NewsDTO dto = toDTOConverter.apply(dbModel);
		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		return new ModelAndView("news.info", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final NewsDTO dto = toDTOConverter.apply(newsService.get(id));
		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		return new ModelAndView("news.edit", hashMap);
	}

}
