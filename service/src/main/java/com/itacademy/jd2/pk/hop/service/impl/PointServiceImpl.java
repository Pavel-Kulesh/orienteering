package com.itacademy.jd2.pk.hop.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.pk.hop.dao.api.IPointDao;
import com.itacademy.jd2.pk.hop.dao.api.entity.IPoint;
import com.itacademy.jd2.pk.hop.service.IPointService;

@Service
public class PointServiceImpl implements IPointService {
	private static final Logger LOGGER = LoggerFactory.getLogger(PointServiceImpl.class);

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

		dao.insert(entity);
		LOGGER.info("new point created: {}", entity);

	}

	@Override
	public void saveList(List<IPoint> entities) {

		dao.insertList(entities);
		for (IPoint entity : entities) {
			LOGGER.info("new point created: {}", entity);
		}

	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);
		LOGGER.info("delete all points with route_id=" + id);

	}

	@Override
	public void deleteAll() {
		// not support?
		dao.deleteAll();
		LOGGER.info("delete all points");

	}

	@Override
	public IPoint createEntity() {
		return dao.createEntity();
	}

	@Override
	public List<IPoint> selectById(Integer id) {
		
		return dao.selectById(id);
	}

	

}
