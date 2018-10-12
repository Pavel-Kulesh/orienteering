package com.itacademy.jd2.pk.hop.service;

import java.util.List;

import com.itacademy.jd2.pk.hop.dao.api.entity.IEvent;

public interface IEventService {
	IEvent get(Integer id);

	List<IEvent> getAll();

	void save(IEvent entity);

	void delete(Integer id);

	void deleteAll();

	IEvent createEntity();

}
