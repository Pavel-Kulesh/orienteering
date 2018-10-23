package com.itacademy.jd2.pk.hop.service;

import java.util.List;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICustomer;
import com.itacademy.jd2.pk.hop.dao.api.filter.CustomerFilter;

public interface ICustomerService {
	ICustomer get(Integer id);

	List<ICustomer> getAll();

	void save(ICustomer entity);

	void update(ICustomer entity);

	void delete(Integer id);

	void deleteAll();

	ICustomer createEntity();

	List<ICustomer> find(CustomerFilter filter);

	long getCount(CustomerFilter filter);

}
