package com.itacademy.jd2.pk.hop.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICity;
import com.itacademy.jd2.pk.hop.dao.api.entity.ICustomer;
import com.itacademy.jd2.pk.hop.service.ICityService;
import com.itacademy.jd2.pk.hop.service.ICustomerService;
import com.itacademy.jd2.pk.hop.web.dto.RegFormDTO;

@Component
public class CustomerConverterRegFormFromDTO implements Function<RegFormDTO, ICustomer> {
	private ICustomerService customerService;
	private ICityService cityService;

	@Autowired
	public CustomerConverterRegFormFromDTO(ICustomerService customerService, ICityService cityService) {
		super();
		this.customerService = customerService;
		this.cityService = cityService;
	}

	@Override
	public ICustomer apply(RegFormDTO dto) {

		ICustomer entity = customerService.createEntity();
		entity.setName(dto.getName());
		entity.setSurname(dto.getSurname());
		entity.setPhone(dto.getPhone());

		ICity city = cityService.get(dto.getCityId());
		entity.setCity(city);
		return entity;
	}

}
