package com.itacademy.jd2.pk.hop.service;

import java.util.List;

import com.itacademy.jd2.pk.hop.dao.api.entity.INews;
import com.itacademy.jd2.pk.hop.dao.api.filter.NewsFilter;

public interface INewsServise {
	INews get(Integer id);

	List<INews> getAll();

	void save(INews entity);

	void delete(Integer id);

	void deleteAll();

	INews createEntity();

	List<INews> find(NewsFilter filter);

	long getCount(NewsFilter filter);
}
