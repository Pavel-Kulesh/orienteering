package com.itacademy.jd2.pk.hop.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICountry;
import com.itacademy.jd2.pk.hop.dao.api.entity.IEvent;
import com.itacademy.jd2.pk.hop.dao.api.entity.Type;

public class EventServiceTest extends AbstractTest {
	@Test
	public void testCreated() {
		IEvent entity = saveNewEvent();

		IEvent entityFromDb = eventService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getName());
		assertNotNull(entityFromDb.getCountryId());
		assertNotNull(entityFromDb.getCreatorId());
		assertNotNull(entityFromDb.getInfo());
		assertNotNull(entityFromDb.getDate());
		assertNotNull(entityFromDb.getLatitude());
		assertNotNull(entityFromDb.getLongitude());
		assertNotNull(entityFromDb.getType());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getId(), entityFromDb.getId());
		assertEquals(entity.getCountryId(), entityFromDb.getCountryId());
		assertEquals(entity.getName(), entityFromDb.getName());
		assertEquals(entity.getCreatorId(), entityFromDb.getCreatorId());
		assertEquals(entity.getInfo(), entityFromDb.getInfo());
		assertEquals(entity.getDate(), entityFromDb.getDate());
		assertEquals(entity.getLatitude(), entityFromDb.getLatitude());
		assertEquals(entity.getLongitude(), entityFromDb.getLongitude());
		assertEquals(entity.getType(), entityFromDb.getType());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertEquals(entity.getUpdated(), entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

	}

	@Test
	public void testUpdate() throws InterruptedException {
		IEvent entity = saveNewEvent();
		ICountry newCountry = saveNewCountry();

		String newName = entity.getName() + "_updated";
		String newInfo = entity.getInfo() + "_updated";

		Type[] allTypes = Type.values();
		int randomIndex = Math.max(0, getRANDOM().nextInt(allTypes.length));
		Type newType = allTypes[randomIndex];
		Double newLat = getDoubleNumber();
		Double newLong = getDoubleNumber();

		entity.setName(newName);
		entity.setCountryId(newCountry.getId());
		entity.setType(newType);
		entity.setInfo(newInfo);

		entity.setLatitude(newLat);
		entity.setLongitude(newLong);

		Thread.sleep(1000);

		Date newDate = new Date();
		entity.setDate(newDate);
		eventService.save(entity);

		IEvent entityFromDb = eventService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getName());
		assertNotNull(entityFromDb.getCountryId());
		assertNotNull(entityFromDb.getCreatorId());
		assertNotNull(entityFromDb.getInfo());
		assertNotNull(entityFromDb.getDate());
		assertNotNull(entityFromDb.getLatitude());
		assertNotNull(entityFromDb.getLongitude());
		assertNotNull(entityFromDb.getType());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());

		assertEquals(entity.getId(), entityFromDb.getId());
		assertEquals(entity.getCreatorId(), entityFromDb.getCreatorId());
		assertEquals(newName, entityFromDb.getName());
		assertEquals(newInfo, entityFromDb.getInfo());
		assertEquals(newType, entityFromDb.getType());
		assertEquals(newDate, entityFromDb.getDate());
		assertEquals(newCountry.getId(), entityFromDb.getCountryId());
		assertEquals(newLat, entityFromDb.getLatitude());
		assertEquals(newLong, entityFromDb.getLongitude());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));

	}

	@Test
	public void testGetAll() {
		int initialCount = eventService.getAll().size();
		int randomObjectsCount = getRandomObjectsCount();

		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewEvent();
		}
		List<IEvent> allEntities = eventService.getAll();
		for (IEvent entityFromDb : allEntities) {
			assertNotNull(entityFromDb);
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getName());
			assertNotNull(entityFromDb.getCountryId());
			assertNotNull(entityFromDb.getCreatorId());
			assertNotNull(entityFromDb.getInfo());
			assertNotNull(entityFromDb.getDate());
			assertNotNull(entityFromDb.getLatitude());
			assertNotNull(entityFromDb.getLongitude());
			assertNotNull(entityFromDb.getType());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}
		assertEquals(randomObjectsCount + initialCount, allEntities.size());

	}

	@Test
	public void testDeleteById() {
		IEvent entity = saveNewEvent();
		eventService.delete(entity.getId());
		assertNull(eventService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewEvent();
		eventService.deleteAll();
		assertEquals(0, eventService.getAll().size());
	}
}
