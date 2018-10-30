package com.itacademy.jd2.pk.hop.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.itacademy.jd2.pk.hop.dao.api.entity.IPoint;
@Entity
public class Point extends BaseEntity implements IPoint {
	@Column
	private Integer routeId;
	@Column
	private Double latitude;
	@Column
	private Double longitude;
	@Column
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
