package com.itacademy.jd2.pk.hop.dao.jdbc.impl.entity;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICustomer;
import com.itacademy.jd2.pk.hop.dao.api.entity.IRoute;

public class Route extends BaseEntity implements IRoute {
	private String name;
	
	private ICustomer customer;

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public ICustomer getCustomer() {
		return customer;
	}

	public void setCustomer(ICustomer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Route [name=" + name + ",customer=" + customer + ", getId()="
				+ getId() + ", getCreated()=" + getCreated() + ", getUpdated()=" + getUpdated() + "]";
	}

}