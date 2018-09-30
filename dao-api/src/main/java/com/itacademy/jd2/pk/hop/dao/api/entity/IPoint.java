package com.itacademy.jd2.pk.hop.dao.api.entity;

public interface IPoint extends IBaseEntity {
	Integer getRouteId();

	void setRouteId(Integer routeId);

	Double getLatitude();

	void setLatitude(Double latitude);

	Double getLongitude();

	void setLongitude(Double longitude);

	Integer getDiffTime();

	void setDiffTime(Integer diffTime);
}
