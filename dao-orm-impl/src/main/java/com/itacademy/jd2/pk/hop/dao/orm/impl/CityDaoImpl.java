package com.itacademy.jd2.pk.hop.dao.orm.impl;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.pk.hop.dao.api.ICityDao;
import com.itacademy.jd2.pk.hop.dao.api.entity.ICity;
import com.itacademy.jd2.pk.hop.dao.orm.impl.entity.City;
@Repository
public class CityDaoImpl extends AbstractDaoImpl<ICity, Integer> implements ICityDao {

	protected CityDaoImpl() {
		super(City.class);
	}

	@Override
	public ICity createEntity() {
		City city = new City();
		return city;
	}
}
