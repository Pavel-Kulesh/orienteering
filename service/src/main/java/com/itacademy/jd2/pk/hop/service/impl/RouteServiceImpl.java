package com.itacademy.jd2.pk.hop.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.pk.hop.dao.api.IRouteDao;
import com.itacademy.jd2.pk.hop.dao.api.entity.IRoute;
import com.itacademy.jd2.pk.hop.service.IRouteService;

@Service
public class RouteServiceImpl implements IRouteService {
	@Autowired
	private IRouteDao dao;

	public RouteServiceImpl(IRouteDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public IRoute get(Integer id) {

		IRoute entity = dao.get(id);
		return entity;
	}

	@Override
	public List<IRoute> getAll() {
		List<IRoute> all = dao.selectAll();
		return all;
	}

	@Override
	public void save(IRoute entity) {
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
	public void delete(Integer id) {
		// need delete all point where route_id=id
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		// need delete all point
		dao.deleteAll();
	}

	@Override
	public IRoute createEntity() {
		return dao.createEntity();
	}

}
