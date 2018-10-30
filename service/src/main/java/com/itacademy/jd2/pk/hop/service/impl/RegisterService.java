package com.itacademy.jd2.pk.hop.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICustomer;
import com.itacademy.jd2.pk.hop.dao.api.entity.IUserAccount;
import com.itacademy.jd2.pk.hop.service.ICustomerService;
import com.itacademy.jd2.pk.hop.service.IRegisterService;
import com.itacademy.jd2.pk.hop.service.IUserAccountService;

@Service
public class RegisterService implements IRegisterService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RegisterService.class);

	private ICustomerService customerService;
	private IUserAccountService userAccountService;

	@Autowired
	public RegisterService(ICustomerService customerService, IUserAccountService userAccountService) {
		super();
		this.customerService = customerService;
		this.userAccountService = userAccountService;
	}

	@Override
	public void saveRegisterData(ICustomer customer, IUserAccount userAccount) {
		try {
			userAccountService.save(userAccount);
			customer.setId(userAccount.getId());
			customerService.save(customer);
		} catch (Exception e) {
			LOGGER.info("error registration");
		}

	}

}
