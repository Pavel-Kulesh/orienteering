package com.itacademy.jd2.pk.hop.dao.jdbc.impl.entity;

import com.itacademy.jd2.pk.hop.dao.api.entity.INews;

public class News extends BaseEntity implements INews {
	private String name;
	private String info;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "News [name=" + name + ", info=" + info + ", getId()=" + getId() + ", getCreated()=" + getCreated()
				+ ", getUpdated()=" + getUpdated() + "]";
	}
}