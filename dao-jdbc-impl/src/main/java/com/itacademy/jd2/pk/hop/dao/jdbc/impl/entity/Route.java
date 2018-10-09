package com.itacademy.jd2.pk.hop.dao.jdbc.impl.entity;

import com.itacademy.jd2.pk.hop.dao.api.entity.IRoute;

public class Route extends BaseEntity implements IRoute {
	private String name;
	private String path;
	private String file;
	private Integer userId;

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Route [name=" + name + ", path=" + path + ", file=" + file + ", userId=" + userId + ", getId()="
				+ getId() + ", getCreated()=" + getCreated() + ", getUpdated()=" + getUpdated() + "]";
	}

}