package com.itacademy.jd2.pk.hop.dao.api.entity;

public interface IUserAccount extends IBaseEntity {
	String getEmail();

	void setEmail(String email);

	String getPassword();

	void setPassword(String password);

	Role getRole();

	void setRole(Role role);

	ICustomer getCustomer();

	void setCustomer(ICustomer customer);
}
