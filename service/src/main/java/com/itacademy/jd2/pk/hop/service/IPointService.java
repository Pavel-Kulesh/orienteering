package com.itacademy.jd2.pk.hop.service;

import java.util.List;

import com.itacademy.jd2.pk.hop.dao.api.entity.IPoint;

public interface IPointService {
	IPoint get(Integer id);

	List<IPoint> getAll();

	void save(IPoint entity);

	void saveList(List<IPoint> entities);

	void delete(Integer id);

	void deleteAll();

	IPoint createEntity();

}
