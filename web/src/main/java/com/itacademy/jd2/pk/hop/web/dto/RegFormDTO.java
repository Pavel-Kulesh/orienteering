package com.itacademy.jd2.pk.hop.web.dto;

import javax.validation.constraints.Size;

import com.itacademy.jd2.pk.hop.dao.api.entity.Role;

public class RegFormDTO {
	
	private String email;
	
	private String password;
	
	private Role role;
	@Size(min = 1, max = 50)
	private String name;
	@Size(min = 1, max = 50)
	private String surname;

	private String phone;
	
	private Integer cityId;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

}