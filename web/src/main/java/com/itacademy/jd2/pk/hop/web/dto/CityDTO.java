package com.itacademy.jd2.pk.hop.web.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class CityDTO {
	private Integer id;
	@NotEmpty
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
