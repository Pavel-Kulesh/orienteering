package com.itacademy.jd2.pk.hop.service;

import java.util.List;

import com.itacademy.jd2.pk.hop.dao.api.entity.IMap;
import com.itacademy.jd2.pk.hop.dao.api.filter.MapFilter;

public interface IMapService {
	IMap get(Integer id);

	List<IMap> getAll();

	void save(IMap entity);

	void delete(Integer id);

	void deleteAll();

	IMap createEntity();

	List<IMap> find(MapFilter filter);

	long getCount(MapFilter filter);
}
