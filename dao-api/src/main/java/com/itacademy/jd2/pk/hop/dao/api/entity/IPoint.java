package com.itacademy.jd2.pk.hop.dao.api.entity;

public interface IPoint extends IBaseEntity {
	Double getLatitude();

	void setLatitude(Double latitude);

	Double getLongitude();

	void setLongitude(Double longitude);

	Integer getDiffTime();

	void setDiffTime(Integer diffTime);

	IRoute getRoute();

	void setRoute(IRoute route);
}
