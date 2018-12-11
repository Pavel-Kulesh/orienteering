package com.itacademy.jd2.pk.hop.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.pk.hop.dao.api.entity.IPoint;

public interface IPointService {
	IPoint get(Integer id);

	List<IPoint> getAll();

	@Transactional
	void save(IPoint entity);

	@Transactional
	void saveList(List<IPoint> entities);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	List<IPoint> selectByRouteId(Integer id);

	IPoint createEntity();

}
