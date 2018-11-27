package com.itacademy.jd2.pk.hop.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.pk.hop.dao.api.entity.IRoute;
import com.itacademy.jd2.pk.hop.dao.api.filter.RouteFilter;

public interface IRouteService {
	
	IRoute get(Integer id);

	List<IRoute> getAll();

	@Transactional
	void save(IRoute entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IRoute createEntity();

	List<IRoute> find(RouteFilter filter);

	long getCount(RouteFilter filter);
}
