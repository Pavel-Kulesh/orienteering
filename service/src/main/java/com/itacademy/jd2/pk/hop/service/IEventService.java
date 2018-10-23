package com.itacademy.jd2.pk.hop.service;

import java.util.List;

import com.itacademy.jd2.pk.hop.dao.api.entity.IEvent;
import com.itacademy.jd2.pk.hop.dao.api.filter.EventFilter;

public interface IEventService {
	IEvent get(Integer id);

	List<IEvent> getAll();

	void save(IEvent entity);

	void delete(Integer id);

	void deleteAll();

	IEvent createEntity();

	List<IEvent> find(EventFilter filter);

	long getCount(EventFilter filter);

}
