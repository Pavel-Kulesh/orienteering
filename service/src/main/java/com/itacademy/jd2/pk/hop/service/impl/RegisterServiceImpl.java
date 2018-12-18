package com.itacademy.jd2.pk.hop.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICustomer;
import com.itacademy.jd2.pk.hop.dao.api.entity.IUserAccount;
import com.itacademy.jd2.pk.hop.service.ICustomerService;
import com.itacademy.jd2.pk.hop.service.IMailService;
import com.itacademy.jd2.pk.hop.service.IRegisterService;
import com.itacademy.jd2.pk.hop.service.IUserAccountService;

@Service
public class RegisterServiceImpl implements IRegisterService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RegisterServiceImpl.class);
	private ICustomerService customerService;
	private IUserAccountService userAccountService;
	private IMailService mailServise;

	@Autowired
	public RegisterServiceImpl(ICustomerService customerService, IUserAccountService userAccountService,
			IMailService sendMailServise) {
		super();
		this.customerService = customerService;
		this.userAccountService = userAccountService;
		this.mailServise = sendMailServise;
	}

	@Override
	public void saveRegisterData(ICustomer customer, IUserAccount userAccount) {
		userAccountService.save(userAccount);
		customer.setUserAccount(userAccount);
		customerService.save(customer);
		mailServise.sendEmail(userAccount.getEmail());
		LOGGER.info("registration complite");
	}

	public void setMailServise(IMailService mailServise) {
		this.mailServise = mailServise;
	}
}
