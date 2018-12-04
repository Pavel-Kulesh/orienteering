package com.itacademy.jd2.pk.hop.service;

import javax.transaction.Transactional;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICustomer;
import com.itacademy.jd2.pk.hop.dao.api.entity.IUserAccount;

public interface IRegisterService {
	@Transactional
	void saveRegisterData(ICustomer customer, IUserAccount userAccount);
	void setMailServise(IMailSevice mailServise);
}
