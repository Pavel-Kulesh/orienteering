package com.itacademy.jd2.pk.hop.service;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICustomer;
import com.itacademy.jd2.pk.hop.dao.api.entity.IUserAccount;

public interface IRegisterService {
	void saveRegisterData(ICustomer customer, IUserAccount userAccount);

}
