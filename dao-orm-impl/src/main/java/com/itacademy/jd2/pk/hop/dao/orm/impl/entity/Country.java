package com.itacademy.jd2.pk.hop.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICountry;

@Entity
public class Country extends BaseEntity implements ICountry {
	@Column
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Country [name=" + name + ", getId()=" + getId() + "]";
	}

	

}
