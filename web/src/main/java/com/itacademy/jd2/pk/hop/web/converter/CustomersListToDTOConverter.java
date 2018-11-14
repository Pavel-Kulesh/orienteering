package com.itacademy.jd2.pk.hop.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICustomer;
import com.itacademy.jd2.pk.hop.web.dto.CustomerDTO;

@Component
public class CustomersListToDTOConverter implements Function<ICustomer, CustomerDTO> {

	@Override
	public CustomerDTO apply(ICustomer entity) {

		CustomerDTO dto = new CustomerDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setSurname(entity.getSurname());
		return dto;
	}

}
