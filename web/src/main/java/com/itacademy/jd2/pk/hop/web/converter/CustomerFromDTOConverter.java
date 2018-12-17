package com.itacademy.jd2.pk.hop.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICity;
import com.itacademy.jd2.pk.hop.dao.api.entity.ICustomer;
import com.itacademy.jd2.pk.hop.dao.api.entity.IUserAccount;
import com.itacademy.jd2.pk.hop.service.ICityService;
import com.itacademy.jd2.pk.hop.service.ICustomerService;
import com.itacademy.jd2.pk.hop.service.IUserAccountService;
import com.itacademy.jd2.pk.hop.web.dto.CustomerDTO;

@Component
public class CustomerFromDTOConverter implements Function<CustomerDTO, ICustomer> {

	private IUserAccountService userAccountService;
	private ICustomerService customerService;
	private ICityService cityService;

	@Autowired
	public CustomerFromDTOConverter(ICustomerService customerService, ICityService cityService,
			IUserAccountService userAccountService) {
		super();
		this.customerService = customerService;
		this.cityService = cityService;
		this.userAccountService = userAccountService;
	}

	@Override
	public ICustomer apply(CustomerDTO dto) {

		ICustomer entity = customerService.createEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setSurname(dto.getSurname());
		entity.setPhone(dto.getPhone());
		ICity city = cityService.get(dto.getCityId());
		IUserAccount userAccount = userAccountService.get(dto.getId());
		userAccount.setRole(dto.getRole());
		entity.setUserAccount(userAccount);
		entity.setCity(city);
		return entity;
	}

}
