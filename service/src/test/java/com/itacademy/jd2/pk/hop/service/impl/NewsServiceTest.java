package com.itacademy.jd2.pk.hop.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.pk.hop.dao.api.entity.INews;

public class NewsServiceTest extends AbstractTest {
	@Test
	public void testCreated() {
		INews entity = saveNewNews();
		INews entityFromDb = newsService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(entity.getName(), entityFromDb.getName());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getInfo());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getInfo(), entityFromDb.getInfo());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

	}

	@Test
	public void testUpdate() throws InterruptedException {
		INews entity = saveNewNews();
		String newName = entity.getName() + "_updated";
		entity.setName(newName);
		String newInfo = entity.getInfo() + "_updated";
		entity.setInfo(newInfo);
		Thread.sleep(1000);
		newsService.save(entity);

		INews entityFromDb = newsService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(newName, entityFromDb.getName());
		assertNotNull(entityFromDb.getId());
		assertEquals(entity.getId(), entityFromDb.getId());
		assertEquals(newInfo, entityFromDb.getInfo());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		// assertEquals(entity.getCreated(), entityFromDb.getCreated());
		// assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));

	}

	@Test
	public void testGetAll() {
		int initialCount = newsService.getAll().size();
		int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewNews();
		}
		List<INews> allEntities = newsService.getAll();
		for (INews entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getName());
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getInfo());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}
		assertEquals(randomObjectsCount + initialCount, allEntities.size());

	}

	@Test
	public void testDeleteById() {
		INews entity = saveNewNews();
		newsService.delete(entity.getId());
		assertNull(newsService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewNews();
		newsService.deleteAll();

		assertEquals(0, newsService.getAll().size());
	}

}
