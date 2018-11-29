package com.itacademy.jd2.pk.hop.dao.api.entity;

import java.util.Date;
import java.util.Set;

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

	Set<ICustomer> getCustomersList();

	void setCustomersList(Set<ICustomer> customersList);

	Integer getVersion();

	void setVersion(Integer version);

}
