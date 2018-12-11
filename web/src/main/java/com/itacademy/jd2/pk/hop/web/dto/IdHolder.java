package com.itacademy.jd2.pk.hop.web.dto;

import javax.validation.constraints.NotNull;

public class IdHolder {
	 @NotNull(message="select route")
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
