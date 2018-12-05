package com.itacademy.jd2.pk.hop.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICustomer;
import com.itacademy.jd2.pk.hop.dao.api.entity.IRoute;
import com.itacademy.jd2.pk.hop.service.ICustomerService;
import com.itacademy.jd2.pk.hop.service.IRouteService;
import com.itacademy.jd2.pk.hop.web.dto.RouteDTO;

@Component
public class RouteFromDTOConverter implements Function<RouteDTO, IRoute> {

	private IRouteService routeSerise;
	private ICustomerService customerService;

	@Autowired
	public RouteFromDTOConverter(IRouteService routeSerise, ICustomerService customerService) {
		super();
		this.routeSerise = routeSerise;
		this.customerService = customerService;
	}

	@Override
	public IRoute apply(RouteDTO dto) {
		IRoute entity = routeSerise.createEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		ICustomer customer = customerService.get(dto.getCustomerId());
		entity.setCustomer(customer);
		return entity;
	}

}
