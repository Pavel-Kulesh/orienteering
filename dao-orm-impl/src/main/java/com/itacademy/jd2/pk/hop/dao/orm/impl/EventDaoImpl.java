package com.itacademy.jd2.pk.hop.dao.orm.impl;

import java.util.List;

import com.itacademy.jd2.pk.hop.dao.api.IEventDao;
import com.itacademy.jd2.pk.hop.dao.api.entity.IEvent;
import com.itacademy.jd2.pk.hop.dao.api.filter.EventFilter;
import com.itacademy.jd2.pk.hop.dao.orm.impl.entity.Event;

public class EventDaoImpl extends AbstractDaoImpl<IEvent, Integer> implements IEventDao {

	protected EventDaoImpl() {
		super(Event.class);
	}

	@Override
	public IEvent createEntity() {
		IEvent event = new Event();
		return event;
	}

	@Override
	public List<IEvent> find(EventFilter filter) {
		throw new RuntimeException("not implemented");
	}

	@Override
	public long getCount(EventFilter filter) {
		throw new RuntimeException("not implemented");
	}

	@Override
	public List<IEvent> getEventsByCustomer(Integer id) {
		throw new RuntimeException("not implemented");
	}

}
