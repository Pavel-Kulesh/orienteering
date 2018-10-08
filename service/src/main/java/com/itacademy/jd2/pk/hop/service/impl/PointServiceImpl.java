package com.itacademy.jd2.pk.hop.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.pk.hop.dao.api.IPointDao;
import com.itacademy.jd2.pk.hop.dao.api.entity.IPoint;
import com.itacademy.jd2.pk.hop.service.IPointService;

@Service
public class PointServiceImpl implements IPointService {

	private IPointDao dao;

	@Autowired
	public PointServiceImpl(IPointDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public IPoint get(Integer id) {
		IPoint entity = dao.get(id);
		return entity;
	}

	@Override
	public List<IPoint> getAll() {
		List<IPoint> all = dao.selectAll();
		return all;
	}

	@Override
	public void save(IPoint entity) {
		Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			dao.insert(entity);
		} else {
			dao.update(entity);
		}
	}

	@Override
	public void save(IPoint... entity) {

	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		// not support?
		dao.deleteAll();
	}

	@Override
	public IPoint createEntity() {
		return dao.createEntity();
	}

}
