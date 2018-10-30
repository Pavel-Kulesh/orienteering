package com.itacademy.jd2.pk.hop.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.itacademy.jd2.pk.hop.dao.api.entity.INews;
@Entity
public class News extends BaseEntity implements INews {
	@Column
	private String name;
	@Column
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
		return "News [name=" + name + ", info=" + info + ", Id()=" + getId() + ", Created()=" + getCreated()
				+ ", Updated()=" + getUpdated() + "]";
	}
}