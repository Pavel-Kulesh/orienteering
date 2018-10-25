package com.itacademy.jd2.pk.hop.service;

import java.util.List;

import com.itacademy.jd2.pk.hop.dao.api.entity.IUserAccount;

public interface IUserAccountService {
	IUserAccount get(Integer id);
	IUserAccount getByEmail(String email);

	List<IUserAccount> getAll();

	void save(IUserAccount entity);

	void delete(Integer id);

	void deleteAll();

	IUserAccount createEntity();
}
