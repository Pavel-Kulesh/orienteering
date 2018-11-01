package com.itacademy.jd2.pk.hop.dao.orm.impl;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.pk.hop.dao.api.ICountryDao;
import com.itacademy.jd2.pk.hop.dao.api.entity.ICountry;
import com.itacademy.jd2.pk.hop.dao.orm.impl.entity.Country;
@Repository
public class CountryDaoImpl extends AbstractDaoImpl<ICountry, Integer> implements ICountryDao {

	protected CountryDaoImpl() {
		super(Country.class);
	}

	@Override
	public ICountry createEntity() {
		Country country = new Country();
		return country;
	}

}
