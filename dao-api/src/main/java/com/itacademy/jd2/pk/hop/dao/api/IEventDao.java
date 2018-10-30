package com.itacademy.jd2.pk.hop.dao.api;

import java.util.List;

import com.itacademy.jd2.pk.hop.dao.api.entity.IEvent;
import com.itacademy.jd2.pk.hop.dao.api.filter.EventFilter;

public interface IEventDao extends IDao<IEvent, Integer> {
	List<IEvent> find(EventFilter filter);

	long getCount(EventFilter filter);

	List<IEvent> getEventsByCustomer(Integer id);

}
