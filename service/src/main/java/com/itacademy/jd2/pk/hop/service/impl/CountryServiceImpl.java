package com.itacademy.jd2.pk.hop.service.impl;

import java.util.Date;
import java.util.List;

import com.itacademy.jd2.pk.hop.dao.api.ICountryDao;
import com.itacademy.jd2.pk.hop.dao.api.entity.ICountry;
import com.itacademy.jd2.pk.hop.dao.jdbc.impl.CountryDaoImpl;
import com.itacademy.jd2.pk.hop.service.ICountryService;

public class CountryServiceImpl implements ICountryService {

	private ICountryDao dao = new CountryDaoImpl();

	@Override
	public ICountry get(Integer id) {
		final ICountry entity = dao.get(id);
		return entity;
	}

	@Override
	public List<ICountry> getAll() {
		final List<ICountry> all = dao.selectAll();
		return all;
	}

	@Override
	public void save(ICountry entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			dao.insert(entity);
		} else {
			dao.update(entity);
		}

	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);

	}

	@Override
	public void deleteAll() {
		dao.deleteAll();

	}

	@Override
	public ICountry createEntity() {
		return dao.createEntity();
	}

}
