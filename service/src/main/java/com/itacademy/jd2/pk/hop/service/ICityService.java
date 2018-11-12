package com.itacademy.jd2.pk.hop.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICity;

public interface ICityService {

	ICity get(Integer id);

	List<ICity> getAll();

	List<ICity> getByCountry(Integer id);

	@Transactional
	void save(ICity entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	ICity createEntity();

}
