package com.itacademy.jd2.pk.hop.dao.jdbc.impl.entity;

import com.itacademy.jd2.pk.hop.dao.api.entity.IPoint;
import com.itacademy.jd2.pk.hop.dao.api.entity.IRoute;

public class Point extends BaseEntity implements IPoint {
	
	
	private IRoute route;
	private Double latitude;
	private Double longitude;
	private Integer diffTime;

	
	
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

	public IRoute getRoute() {
		return route;
	}

	public void setRoute(IRoute route) {
		this.route = route;
	}

	@Override
	public String toString() {
		return "Point [route=" + route + ", getId()=" + getId() + "]";
	}

	
	

}
