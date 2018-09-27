package com.itacademy.jd2.pk.hop.service.impl;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICountry;

import com.itacademy.jd2.pk.hop.service.ICountryService;

public class AbstractTestOrient {
	protected ICountryService countryService = new CountryServiceImpl();
	private static Random RANDOM = new Random();
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractTestOrient.class);

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
}
