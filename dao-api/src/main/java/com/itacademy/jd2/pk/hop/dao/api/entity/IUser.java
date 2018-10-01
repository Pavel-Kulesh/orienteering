package com.itacademy.jd2.pk.hop.dao.api.entity;

public interface IUser extends IBaseEntity {
	String getName();

	void setName(String name);

	String getSurname();

	void setSurname(String surname);

	String getPhone();

	void setPhone(String phone);

	Integer getCityId();

	void setCityId(Integer cityId);
}
