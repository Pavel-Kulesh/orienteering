package com.itacademy.jd2.pk.hop.dao.jdbc.impl.entity;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICity;
import com.itacademy.jd2.pk.hop.dao.api.entity.ICountry;

public class City extends BaseEntity implements ICity {
	private String name;

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
		return "City [name=" + name + ", country=" + country + ", getId()=" + getId() + "]";
	}

	

	
	

}
