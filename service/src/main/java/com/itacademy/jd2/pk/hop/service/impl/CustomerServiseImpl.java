package com.itacademy.jd2.pk.hop.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.pk.hop.dao.api.ICustomerDao;
import com.itacademy.jd2.pk.hop.dao.api.entity.ICustomer;
import com.itacademy.jd2.pk.hop.dao.api.filter.CustomerFilter;
import com.itacademy.jd2.pk.hop.service.ICustomerService;

@Service
public class CustomerServiseImpl implements ICustomerService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiseImpl.class);

	/*
	 * @Autowired private IUserAccountDao userDao;
	 */
	private ICustomerDao dao;

	@Autowired
	public CustomerServiseImpl(ICustomerDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public ICustomer get(Integer id) {
		ICustomer entity = dao.get(id);
		return entity;
	}

	@Override
	public List<ICustomer> getAll() {
		List<ICustomer> all = dao.selectAll();
		return all;
	}

	@Override
	public void save(ICustomer entity) {
		Date modifedOn = new Date();

		entity.setCreated(modifedOn);
		entity.setUpdated(modifedOn);
		dao.insert(entity);
		LOGGER.info("new customer created: {}", entity);

	}

	@Override
	public void update(ICustomer entity) {
		Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		dao.update(entity);
		LOGGER.debug("customer updated: {}" + entity);
	}

	@Override
	public void delete(Integer id) {
		LOGGER.info("delete customer with id=" + id);
		dao.delete(id);

	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all customers");
		dao.deleteAll();
	}

	@Override
	public ICustomer createEntity() {
		return dao.createEntity();
	}

	@Override
	public List<ICustomer> find(CustomerFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(CustomerFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public List<ICustomer> showParticipant(Integer id) {

		return dao.showParticipant(id);
	}

}
