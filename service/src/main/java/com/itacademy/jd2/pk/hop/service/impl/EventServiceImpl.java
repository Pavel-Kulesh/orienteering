package com.itacademy.jd2.pk.hop.service.impl;

import java.util.Date;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.pk.hop.dao.api.IEventDao;
import com.itacademy.jd2.pk.hop.dao.api.entity.IEvent;
import com.itacademy.jd2.pk.hop.dao.api.filter.EventFilter;
import com.itacademy.jd2.pk.hop.service.IEventService;

@Service
public class EventServiceImpl implements IEventService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EventServiceImpl.class);

	private IEventDao dao;

	@Autowired
	public EventServiceImpl(IEventDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public IEvent get(Integer id) {
		IEvent entity = dao.get(id);
		return entity;
	}

	@Override
	public List<IEvent> getAll() {
		List<IEvent> all = dao.selectAll();
		return all;
	}

	@Override
	public void save(IEvent entity) {
		Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			dao.insert(entity);
			LOGGER.info("new event created: {}", entity);
		} else {
			dao.update(entity);
			LOGGER.debug("event updated: {}", entity);

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
	public IEvent createEntity() {
		return dao.createEntity();
	}

	@Override
	public List<IEvent> find(EventFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(EventFilter filter) {
		return dao.getCount(filter);
	}

}
