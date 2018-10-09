package com.itacademy.jd2.pk.hop.service.impl;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

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
		// -------------------------
		System.out.println(entity.getCreated());
		// -------------------------
		Thread.sleep(1000);
		cityService.save(entity);
		// -------------------------
		System.out.println(entity.getUpdated());
		// -------------------------
		final ICity entityFromDb = cityService.get(entity.getId());
		// -------------------------
		System.out.println(entityFromDb.getCreated());
		System.out.println(entityFromDb.getUpdated());
		// -------------------------
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

	@Test
	public void testGetAll() {
		int initialCount = cityService.getAll().size();
		int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewCity();
		}
		List<ICity> allEntities = cityService.getAll();
		for (ICity entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getName());
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCountryId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}
		assertEquals(randomObjectsCount + initialCount, allEntities.size());

	}

	@Test
	public void testDeleteById() {
		ICity entity = saveNewCity();
		cityService.delete(entity.getId());
		assertNull(cityService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewCity();
		cityService.deleteAll();
		assertEquals(0, cityService.getAll().size());

	}

}
