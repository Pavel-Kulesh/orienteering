package com.itacademy.jd2.pk.hop.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.pk.hop.dao.api.INewsDao;
import com.itacademy.jd2.pk.hop.dao.api.entity.INews;
import com.itacademy.jd2.pk.hop.service.INewsServise;

@Service
public class NewsServiceImpl implements INewsServise {
	private INewsDao dao;
	private static final Logger LOGGER = LoggerFactory.getLogger(NewsServiceImpl.class);
	
	@Autowired
	public NewsServiceImpl(INewsDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public INews get(Integer id) {
		INews entity = dao.get(id);
		return entity;
	}

	@Override
	public List<INews> getAll() {
		List<INews> all = dao.selectAll();
		return all;
	}

	@Override
	public void save(INews entity) {
		Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			dao.insert(entity);
			LOGGER.info("new news created: {}", entity);
			
		} else {
			dao.update(entity);
			LOGGER.debug("news updated: {}", entity);
			
		}
	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);
		LOGGER.info("delete news with id=" + id);
	}

	@Override
	public void deleteAll() {
		dao.deleteAll();
		LOGGER.info("delete all news");
		
	}

	@Override
	public INews createEntity() {
		return dao.createEntity();
	}

}
