package com.itacademy.jd2.pk.hop.dao.api.entity;

public interface ICity extends IBaseEntity {

	String getName();

	void setName(String name);

	ICountry getCountry();

	void setCountry(ICountry country);
}
