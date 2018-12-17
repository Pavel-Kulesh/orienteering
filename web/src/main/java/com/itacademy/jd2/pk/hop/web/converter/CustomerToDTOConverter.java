package com.itacademy.jd2.pk.hop.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICustomer;
import com.itacademy.jd2.pk.hop.web.dto.CustomerDTO;

@Component
public class CustomerToDTOConverter implements Function<ICustomer, CustomerDTO> {

	@Override
	public CustomerDTO apply(ICustomer entity) {

		CustomerDTO dto = new CustomerDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setSurname(entity.getSurname());
		dto.setCityId(entity.getCity().getId());
		dto.setPhone(entity.getPhone());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		dto.setRole(entity.getUserAccount().getRole());
		dto.setCityName(entity.getCity().getName());
		return dto;
	}

}
