package com.itacademy.jd2.pk.hop.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICustomer;
import com.itacademy.jd2.pk.hop.dao.api.entity.IMap;
import com.itacademy.jd2.pk.hop.service.ICustomerService;
import com.itacademy.jd2.pk.hop.service.IMapService;
import com.itacademy.jd2.pk.hop.web.dto.MapDTO;

@Component
public class MapFromDTOConverter implements Function<MapDTO, IMap> {
	private ICustomerService customerService;
	private IMapService mapService;

	@Autowired
	public MapFromDTOConverter(ICustomerService customerService, IMapService mapService) {
		super();
		this.customerService = customerService;
		this.mapService = mapService;
	}

	@Override
	public IMap apply(MapDTO dto) {
		IMap entity = mapService.get(dto.getId());
		if (entity==null) {
			entity = mapService.createEntity();
			entity.setId(dto.getId());
			entity.setName(dto.getName());
				
			ICustomer customer = customerService.get(dto.getCustomerId());
			entity.setCustomer(customer);
			entity.setLatitude1(dto.getLatitude1());
			entity.setLatitude2(dto.getLatitude2());
			entity.setLongitude1(dto.getLongitude1());
			entity.setLongitude2(dto.getLongitude2());
		} else {
			entity.setId(dto.getId());
			entity.setName(dto.getName());
				
			ICustomer customer = customerService.get(dto.getCustomerId());
			entity.setCustomer(customer);
			entity.setLatitude1(dto.getLatitude1());
			entity.setLatitude2(dto.getLatitude2());
			entity.setLongitude1(dto.getLongitude1());
			entity.setLongitude2(dto.getLongitude2());

		}
		
		/*IMap entity = mapService.createEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
			
		ICustomer customer = customerService.get(dto.getCustomerId());
		entity.setCustomer(customer);
		entity.setLatitude1(dto.getLatitude1());
		entity.setLatitude2(dto.getLatitude2());
		entity.setLongitude1(dto.getLongitude1());
		entity.setLongitude2(dto.getLongitude2());
		*/
		

		return entity;
	}

}
