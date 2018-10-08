package com.itacademy.jd2.pk.hop.service;

import java.util.List;

import com.itacademy.jd2.pk.hop.dao.api.entity.INews;

public interface INewsServise {
	INews get(Integer id);

	List<INews> getAll();

	void save(INews entity);

	void delete(Integer id);

	void deleteAll();

	INews createEntity();
}
