package com.itacademy.jd2.pk.hop.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.pk.hop.dao.api.entity.IEvent;
import com.itacademy.jd2.pk.hop.dao.api.filter.EventFilter;

public interface IEventService {
	IEvent get(Integer id);

	List<IEvent> getAll();

	@Transactional
	void save(IEvent entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IEvent createEntity();

	List<IEvent> find(EventFilter filter);

	long getCount(EventFilter filter);

	List<IEvent> getEventsByCustomer(Integer id);

	@Transactional
	void addCustomerToEvent(Integer customerId, Integer eventId);

	@Transactional
	void deleteCustomerFromEvent(Integer customerId, Integer eventId);

	
	boolean checkExistCustomerInEvent(Integer customerId, Integer eventId);
}
