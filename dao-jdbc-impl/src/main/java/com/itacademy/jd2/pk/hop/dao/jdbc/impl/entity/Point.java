package com.itacademy.jd2.pk.hop.dao.jdbc.impl.entity;

import com.itacademy.jd2.pk.hop.dao.api.entity.IPoint;

public class Point extends BaseEntity implements IPoint {
	private Integer routeId;
	private Double latitude;
	private Double longitude;
	private Integer diffTime;

	public Integer getRouteId() {
		return routeId;
	}

	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Integer getDiffTime() {
		return diffTime;
	}

	public void setDiffTime(Integer diffTime) {
		this.diffTime = diffTime;
	}

	@Override
	public String toString() {
		return "Point [routeId=" + routeId + ", latitude=" + latitude + ", longitude=" + longitude + ", diffTime="
				+ diffTime + ", getId()=" + getId() + ", getCreated()=" + getCreated() + ", getUpdated()="
				+ getUpdated() + "]";
	}

}
