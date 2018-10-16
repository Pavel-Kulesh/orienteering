package com.itacademy.jd2.pk.hop.dao.jdbc.impl.entity;

import java.math.BigDecimal;

import com.itacademy.jd2.pk.hop.dao.api.entity.IMap;

public class Map extends BaseEntity implements IMap {
	private String name;
	private String path;
	private String file;
	private Integer userId;
	private BigDecimal latitude1;
	private BigDecimal latitude2;
	private BigDecimal longitude1;
	private BigDecimal longitude2;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public BigDecimal getLatitude1() {
		return latitude1;
	}

	public void setLatitude1(BigDecimal latitude1) {
		this.latitude1 = latitude1;
	}

	public BigDecimal getLatitude2() {
		return latitude2;
	}

	public void setLatitude2(BigDecimal latitude2) {
		this.latitude2 = latitude2;
	}

	public BigDecimal getLongitude1() {
		return longitude1;
	}

	public void setLongitude1(BigDecimal longitude1) {
		this.longitude1 = longitude1;
	}

	public BigDecimal getLongitude2() {
		return longitude2;
	}

	public void setLongitude2(BigDecimal longitude2) {
		this.longitude2 = longitude2;
	}

	@Override
	public String toString() {
		return "Map [name=" + name + ", path=" + path + ", file=" + file + ", userId=" + userId + ", latitude1="
				+ latitude1 + ", latitude2=" + latitude2 + ", longitude1=" + longitude1 + ", longitude2=" + longitude2
				+ ", Id()=" + getId() + ", Created()=" + getCreated() + ", Updated()=" + getUpdated() + "]";
	}

}
