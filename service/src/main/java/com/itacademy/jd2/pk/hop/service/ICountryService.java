package com.itacademy.jd2.pk.hop.service;

import java.util.List;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICountry;

public interface ICountryService {
	ICountry get(Integer id);

	List<ICountry> getAll();

	void save(ICountry entity);

	void delete(Integer id);

	void deleteAll();

	ICountry createEntity();
}
