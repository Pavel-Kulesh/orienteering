package com.itacademy.jd2.pk.hop.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICustomer;
import com.itacademy.jd2.pk.hop.dao.api.entity.IRoute;
import com.itacademy.jd2.pk.hop.dao.api.entity.TypeTrack;

@Entity
public class Route extends BaseEntity implements IRoute {
	@Column
	private String name;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Customer.class)
	private ICustomer customer;
	
	@Column
	@Enumerated(EnumType.STRING)
	private TypeTrack track;

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
		return "Route [name=" + name + ", customer=" + customer + ", getId()=" + getId() + "]";
	}

	public TypeTrack getTrack() {
		return track;
	}

	public void setTrack(TypeTrack track) {
		this.track = track;
	}

	

}