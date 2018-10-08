package com.itacademy.jd2.pk.hop.service;

import java.util.List;

import com.itacademy.jd2.pk.hop.dao.api.entity.IRoute;

public interface IRouteService {
	IRoute get(Integer id);

	List<IRoute> getAll();

	void save(IRoute entity);

	void delete(Integer id);

	void deleteAll();

	IRoute createEntity();
}
