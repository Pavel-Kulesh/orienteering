package com.itacademy.jd2.pk.hop.dao.api;

import java.util.List;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICustomer;
import com.itacademy.jd2.pk.hop.dao.api.filter.CustomerFilter;

public interface ICustomerDao extends IDao<ICustomer, Integer> {
	List<ICustomer> find(CustomerFilter filter);

	long getCount(CustomerFilter filter);
}
