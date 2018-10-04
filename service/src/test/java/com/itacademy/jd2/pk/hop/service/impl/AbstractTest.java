package com.itacademy.jd2.pk.hop.service.impl;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICity;
import com.itacademy.jd2.pk.hop.dao.api.entity.ICountry;
import com.itacademy.jd2.pk.hop.service.ICityService;
import com.itacademy.jd2.pk.hop.service.ICountryService;

@SpringJUnitConfig(locations = "classpath:service-context.xml")
public class AbstractTest {
	@Autowired
	protected ICountryService countryService;
	@Autowired
	protected ICityService cityService;

	private static Random RANDOM = new Random();

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractTest.class);

	@BeforeEach
	public void setUpMethod() {
		LOGGER.info("setUpMethod:");
		// clean DB recursive
		// modelService.deleteAll();
		countryService.deleteAll();
	}

	protected String getRandomPrefix() {
		return RANDOM.nextInt(99999) + "";

	}

	protected int getRandomObjaectCount() {
		return RANDOM.nextInt(9) + 1;
	}

	public Random getRANDOM() {
		return RANDOM;
	}

	protected ICountry saveNewCountry() {
		ICountry entity = countryService.createEntity();
		entity.setName("country-" + getRandomPrefix());
		countryService.save(entity);
		return entity;
	}

	protected ICity saveNewCity() {
		ICity entity = cityService.createEntity();
		entity.setName("city-" + getRandomPrefix());
		entity.setCountryId((Integer) getRandomObjaectCount());

		cityService.save(entity);
		return entity;
	}

}
