package com.itacademy.jd2.pk.hop.service.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.pk.hop.dao.api.entity.IPoint;
import com.itacademy.jd2.pk.hop.dao.api.entity.IRoute;

public class PointServiceTest extends AbstractTest {

	@Test
	public void testCreated() {
		IPoint entity = saveNewPoint();
		// entity.setDiffTime(null);
		// check code => created + updated some times can be null
		// System.out.println(entity.getId());

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

	@Test
	public void testGetAll() {
		int initialCount = pointService.getAll().size();
		int randomObjectsCount = getRandomObjectsCount();

		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewPoint();
		}
		List<IPoint> allEntities = pointService.getAll();
		for (IPoint entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getLatitude());
			assertNotNull(entityFromDb.getLongitude());
			assertNotNull(entityFromDb.getRouteId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}
		assertEquals(randomObjectsCount + initialCount, allEntities.size());

	}

	@Test
	public void testSaveList() {

		List<IPoint> pointList = new ArrayList<>();

		int initialCount = pointService.getAll().size();
		int randomObjectsCount = getRandomObjectsCount();

		for (int i = 0; i < randomObjectsCount; i++) {
			IPoint entity = pointService.createEntity();
			IRoute route = saveNewRoute();
			entity.setRouteId(route.getId());

			entity.setLatitude(getDoubleNumber());
			entity.setLongitude(getDoubleNumber());
			entity.setCreated(new Date());
			entity.setUpdated(new Date());
			entity.setDiffTime(getRandomObjectsCount());
			pointList.add(entity);

		}

		pointService.saveList(pointList);

		List<IPoint> allEntities = pointService.getAll();

		for (IPoint entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getLatitude());
			assertNotNull(entityFromDb.getLongitude());
			assertNotNull(entityFromDb.getRouteId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}
		assertNotNull(pointList);
		assertEquals(randomObjectsCount + initialCount, allEntities.size());

	}

	@Test
	public void testDeleteAll() {
		saveNewPoint();
		pointService.deleteAll();
		assertEquals(0, pointService.getAll().size());
	}

	@Test
	public void testDeleteById() {

		IPoint point = saveNewPoint();
		System.out.println(point.getRouteId() + "--------------route_id");
		pointService.delete(point.getRouteId());
		List<IPoint> allEntities = pointService.getAll();

		boolean idExist = false;

		for (IPoint entity : allEntities) {
			if (entity.getRouteId().equals(point.getRouteId())) {
				idExist = true;
			}
		}

		assertFalse(idExist);

	}

}
