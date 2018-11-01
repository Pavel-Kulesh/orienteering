package com.itacademy.jd2.pk.hop.dao.api.entity;

import java.util.Date;

public interface IEvent extends IBaseEntity {
	String getName();

	void setName(String name);

	Date getDate();

	void setDate(Date date);

	Type getType();

	void setType(Type type);

	String getInfo();

	void setInfo(String info);

	Double getLatitude();

	void setLatitude(Double latitude);

	Double getLongitude();

	void setLongitude(Double longitude);

	ICountry getCountry();

	void setCountry(ICountry country);

	ICustomer getCustomer();

	void setCustomer(ICustomer customer);

}
