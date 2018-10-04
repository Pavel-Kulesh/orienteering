package com.itacademy.jd2.pk.hop.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICity;

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
		Integer countryId = entity.getCountryId() + getRandomObjaectCount();
		entity.setCountryId(countryId);

		Thread.sleep(5000);
		cityService.save(entity);

		final ICity entityFromDb = cityService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(newName, entityFromDb.getName());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCountryId());
		assertEquals(entity.getCountryId(), entityFromDb.getCountryId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));
	}

}
