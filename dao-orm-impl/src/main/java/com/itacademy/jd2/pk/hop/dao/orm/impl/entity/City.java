package com.itacademy.jd2.pk.hop.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICity;
import com.itacademy.jd2.pk.hop.dao.api.entity.ICountry;

@Entity
public class City extends BaseEntity implements ICity {
	@Column
	private String name;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Country.class)
	private ICountry country;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ICountry getCountry() {
		return country;
	}

	public void setCountry(ICountry country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "City [name=" + name + ", getId()=" + getId() + "]";
	}


}
