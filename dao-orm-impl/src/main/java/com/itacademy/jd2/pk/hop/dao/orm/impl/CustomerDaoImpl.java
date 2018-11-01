package com.itacademy.jd2.pk.hop.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.pk.hop.dao.api.ICustomerDao;
import com.itacademy.jd2.pk.hop.dao.api.entity.ICustomer;
import com.itacademy.jd2.pk.hop.dao.api.filter.CustomerFilter;
import com.itacademy.jd2.pk.hop.dao.orm.impl.entity.Customer;
@Repository
public class CustomerDaoImpl extends AbstractDaoImpl<ICustomer, Integer> implements ICustomerDao {

	protected CustomerDaoImpl() {
		super(Customer.class);

	}

	@Override
	public ICustomer createEntity() {
		Customer customer = new Customer();
		return customer;
	}

	@Override
	public List<ICustomer> find(CustomerFilter filter) {
		throw new RuntimeException("not implemented");
	}

	@Override
	public long getCount(CustomerFilter filter) {
		throw new RuntimeException("not implemented");
	}

	@Override
	public List<ICustomer> getCustomersByEvent(Integer id) {
		throw new RuntimeException("not implemented");
	}

}
