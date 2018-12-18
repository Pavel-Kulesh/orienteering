package com.itacademy.jd2.pk.hop.web.dto;

import java.util.Date;

import javax.validation.constraints.Size;

public class NewsDTO {

	private Integer id;

	@Size(min = 1, max = 150)
	private String name;

	@Size(min = 1)
	private String info;

	private Date created;

	private Date updated;

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(final Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(final Date updated) {
		this.updated = updated;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
