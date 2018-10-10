package com.itacademy.jd2.pk.hop.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.pk.hop.dao.api.entity.IPoint;

public class PointServiceTest extends AbstractTest {
	@Test
	public void testCreated() {
		IPoint entity = saveNewPoint();
		// entity.setDiffTime(null);
		System.out.println(entity.getId());
		IPoint entityFromDb = pointService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getLatitude());
		assertNotNull(entityFromDb.getLongitude());
		assertNotNull(entityFromDb.getRouteId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getId(), entityFromDb.getId());
		assertEquals(entity.getRouteId(), entityFromDb.getRouteId());
		assertEquals(entityFromDb.getLatitude(), entityFromDb.getLatitude());
		assertEquals(entity.getLongitude(), entityFromDb.getLongitude());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertEquals(entity.getUpdated(), entityFromDb.getUpdated());
		assertEquals(entity.getDiffTime(), entityFromDb.getDiffTime());

	}

}
