package com.itacademy.jd2.pk.hop.dao.api.entity;

import java.util.Date;

public interface IEvent extends IBaseEntity {
	String getName();

	void setName(String name);

	Integer getCreatorId();

	void setCreatorId(Integer creatorId);

	Date getDate();

	void setDate(Date date);

	Integer getCountryId();

	void setCountryId(Integer countryId);

	Type getType();

	void setType(Type type);

	String getInfo();

	void setInfo(String info);

	Double getLatitude();

	void setLatitude(Double latitude);

	Double getLongitude();

	void setLongitude(Double longitude);
}
