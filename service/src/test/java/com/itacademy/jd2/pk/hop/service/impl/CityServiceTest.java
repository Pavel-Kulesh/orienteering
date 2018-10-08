package com.itacademy.jd2.pk.hop.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICity;
import com.itacademy.jd2.pk.hop.dao.api.entity.ICountry;

public class CityServiceTest extends AbstractTest {

	@Test
	public void testCreated() {

		ICity entity = saveNewCity();

		ICity entityFromDb = cityService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(entity.getName(), entityFromDb.getName());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCountryId());
		assertEquals(entity.getCountryId(), entityFromDb.getCountryId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

	}

	@Test
	public void testUpdate() throws InterruptedException {
		final ICity entity = saveNewCity();

		String newName = entity.getName() + "_updated";
		entity.setName(newName);
		ICountry country = saveNewCountry();
		entity.setCountryId(country.getId());

		System.out.println(entity.getCreated());

		Thread.sleep(1000);
		cityService.save(entity);

		System.out.println(entity.getUpdated());

		final ICity entityFromDb = cityService.get(entity.getId());

		System.out.println(entityFromDb.getCreated());
		System.out.println(entityFromDb.getUpdated());

		assertNotNull(entityFromDb);
		assertEquals(newName, entityFromDb.getName());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCountryId());
		assertEquals(entityFromDb.getCountryId(), country.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		// assertEquals(entity.getCreated(), entityFromDb.getCreated());
		// assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));
	}

}
