package com.itacademy.jd2.pk.hop.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.pk.hop.dao.api.entity.IRoute;

public class RouteServiceTest extends AbstractTest {
	@Test
	public void testCreated() {
		IRoute entity = saveNewRoute();
		IRoute entityFromDb = routeService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getName());
		assertNotNull(entityFromDb.getPath());
		assertNotNull(entityFromDb.getFile());
		assertNotNull(entityFromDb.getUserId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getName(), entityFromDb.getName());
		assertEquals(entity.getId(), entityFromDb.getId());
		assertEquals(entity.getFile(), entityFromDb.getFile());
		assertEquals(entity.getPath(), entityFromDb.getPath());
		assertEquals(entity.getUserId(), entityFromDb.getUserId());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

	}

	@Test
	public void testUpdate() throws InterruptedException {
		IRoute entity = saveNewRoute();
	
		String newName = entity.getName() + "_updated";
		entity.setName(newName);
		String newPath = entity.getPath() + "_updated";
		entity.setPath(newPath);
	
		Thread.sleep(1000);
		routeService.save(entity);

		IRoute entityFromDb = routeService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getFile());
		assertNotNull(entityFromDb.getCreated());
		assertEquals(entity.getId(), entityFromDb.getId());
		assertEquals(entity.getFile(), entityFromDb.getFile());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertEquals(newName, entityFromDb.getName());
		assertEquals(newPath, entityFromDb.getPath());
		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));

	}

	@Test
	public void testGetAll() {
		int initialCount = routeService.getAll().size();

		int randomObjectsCount = getRandomObjectsCount();

		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewRoute();
		}

		List<IRoute> allEntities = routeService.getAll();
		for (IRoute entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getName());
			assertNotNull(entityFromDb.getPath());
			assertNotNull(entityFromDb.getFile());
			assertNotNull(entityFromDb.getUserId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());

		}
		assertEquals(randomObjectsCount + initialCount, allEntities.size());

	}

	@Test
	public void testDeleteById() {
		IRoute entity = saveNewRoute();
		routeService.delete(entity.getId());
		assertNull(routeService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewRoute();
		routeService.deleteAll();
		assertEquals(0, routeService.getAll().size());
	}
}
