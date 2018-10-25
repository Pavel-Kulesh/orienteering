package com.itacademy.jd2.pk.hop.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICustomer;
import com.itacademy.jd2.pk.hop.service.ICustomerService;
import com.itacademy.jd2.pk.hop.web.dto.RegFormDTO;

@Component
public class CustomerConverterFormDTO implements Function<RegFormDTO, ICustomer> {
	private ICustomerService customerService;

	@Autowired
	public CustomerConverterFormDTO(ICustomerService customerService) {
		super();
		this.customerService = customerService;
	}

	@Override
	public ICustomer apply(RegFormDTO dto) {

		ICustomer entity = customerService.createEntity();
		entity.setName(dto.getName());
		entity.setSurname(dto.getSurname());
		entity.setPhone(dto.getPhone());
		entity.setCityId(dto.getCityId());
		return entity;
	}

}
