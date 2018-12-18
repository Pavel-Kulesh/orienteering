package com.itacademy.jd2.pk.hop.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICountry;

public interface ICountryService {
	ICountry get(Integer id);

	List<ICountry> getAll();

	@Transactional
	void save(ICountry entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	ICountry createEntity();
}
