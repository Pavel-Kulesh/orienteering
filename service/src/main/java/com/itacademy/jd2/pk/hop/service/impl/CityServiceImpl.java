package com.itacademy.jd2.pk.hop.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.pk.hop.dao.api.ICityDao;
import com.itacademy.jd2.pk.hop.dao.api.entity.ICity;
import com.itacademy.jd2.pk.hop.service.ICityService;

@Service
public class CityServiceImpl implements ICityService {

	private ICityDao dao;

	@Autowired
	public CityServiceImpl(ICityDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public ICity get(Integer id) {
		ICity entity = dao.get(id);
		return entity;
	}

	@Override
	public List<ICity> getAll() {
		final List<ICity> all = dao.selectAll();
		return all;
	}

	@Override
	public void save(ICity entity) {

		// need create country?

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
		// not realize this method

	}

	@Override
	public ICity createEntity() {
		return dao.createEntity();
	}

}
