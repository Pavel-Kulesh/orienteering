package com.itacademy.jd2.pk.hop.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.pk.hop.dao.api.entity.IRoute;
import com.itacademy.jd2.pk.hop.web.dto.RouteDTO;

@Component
public class RouteToDTOConverter implements Function<IRoute, RouteDTO> {

	@Override
	public RouteDTO apply(IRoute entity) {
		RouteDTO dto = new RouteDTO();
		dto.setCustomerId(entity.getCustomer().getId());
		dto.setPath(entity.getPath());
		dto.setName(entity.getName());
		dto.setId(entity.getId());
		dto.setCreated(entity.getCreated());
		return dto;
	}

}
