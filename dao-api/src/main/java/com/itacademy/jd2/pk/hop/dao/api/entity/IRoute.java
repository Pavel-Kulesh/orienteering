package com.itacademy.jd2.pk.hop.dao.api.entity;

public interface IRoute extends IBaseEntity {
	String getName();

	void setName(String name);

	ICustomer getCustomer();

	void setCustomer(ICustomer customer);

}
