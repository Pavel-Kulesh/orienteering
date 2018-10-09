package com.itacademy.jd2.pk.hop.service.impl;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICountry;

public class CountryServiceTest extends AbstractTest {

	@Test
	public void testCreated() {

		ICountry entity = saveNewCountry();

		ICountry entityFromDb = countryService.get(entity.getId());

		assertEquals(entity.getName(), entityFromDb.getName());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

	}

	@Test
	public void testUpdate() throws InterruptedException {
		ICountry entity = saveNewCountry();
		String newName = entity.getName() + "_updated";
		entity.setName(newName);
		Thread.sleep(2000);

		countryService.save(entity);

		ICountry entityFromDb = countryService.get(entity.getId());
		assertNotNull(entityFromDb);
		assertEquals(newName, entityFromDb.getName());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		// assertEquals(entity.getCreated(), entityFromDb.getCreated());
		// assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));

	}

	@Test
	public void testGetAll() {
		int initialCount = countryService.getAll().size();
		int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewCountry();
		}

		List<ICountry> allEntities = countryService.getAll();
		for (ICountry entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getName());
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());

		}
		assertEquals(randomObjectsCount + initialCount, allEntities.size());

	}

	@Test
	public void testDeleteById() {
		ICountry entity = saveNewCountry();
		countryService.delete(entity.getId());
		assertNull(countryService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewCountry();
		countryService.deleteAll();
		assertEquals(0, countryService.getAll().size());
	}

}
