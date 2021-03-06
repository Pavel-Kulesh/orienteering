package com.itacademy.jd2.pk.hop.dao.jdbc.impl.entity;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICustomer;
import com.itacademy.jd2.pk.hop.dao.api.entity.IUserAccount;
import com.itacademy.jd2.pk.hop.dao.api.entity.Role;

public class UserAccount extends BaseEntity implements IUserAccount {
	private String email;
	private String password;
	private Role role;
	private ICustomer customer;

	public ICustomer getCustomer() {
		return customer;
	}

	public void setCustomer(ICustomer customer) {
		this.customer = customer;
	}

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

	@Override
	public String toString() {
		return "UserAccount [email=" + email + ", password=" + password + ", role=" + role + ", getId()=" + getId()
				+ ", getCreated()=" + getCreated() + ", getUpdated()=" + getUpdated() + "]";
	}

}
