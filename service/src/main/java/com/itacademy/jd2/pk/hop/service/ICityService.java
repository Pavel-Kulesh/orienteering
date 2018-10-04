package com.itacademy.jd2.pk.hop.service;

import java.util.List;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICity;

public interface ICityService {
	ICity get(Integer id);

	List<ICity> getAll();

	void save(ICity entity);

	void delete(Integer id);

	void deleteAll();

	ICity createEntity();

}
