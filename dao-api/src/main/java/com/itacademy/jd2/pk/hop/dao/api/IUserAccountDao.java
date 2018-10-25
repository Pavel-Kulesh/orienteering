package com.itacademy.jd2.pk.hop.dao.api;

import com.itacademy.jd2.pk.hop.dao.api.entity.IUserAccount;

public interface IUserAccountDao extends IDao<IUserAccount, Integer> {
	IUserAccount getByEmail(String email);
}
