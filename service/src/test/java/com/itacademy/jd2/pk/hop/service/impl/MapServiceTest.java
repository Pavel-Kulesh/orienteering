package com.itacademy.jd2.pk.hop.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.pk.hop.dao.api.entity.IMap;

public class MapServiceTest extends AbstractTest {
	@Test
	public void testCreated() {
		IMap entity = saveNewMap();

		IMap entityFromDb = mapService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getName());
		assertNotNull(entityFromDb.getName());
		assertNotNull(entityFromDb.getFile());
		assertNotNull(entityFromDb.getPath());
		assertNotNull(entityFromDb.getUserId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());

		assertEquals(entity.getId(), entityFromDb.getId());
		assertEquals(entity.getName(), entityFromDb.getName());
		assertEquals(entity.getUserId(), entityFromDb.getUserId());
		assertEquals(entity.getPath(), entityFromDb.getPath());
		assertEquals(entity.getFile(), entityFromDb.getFile());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

	}
}
