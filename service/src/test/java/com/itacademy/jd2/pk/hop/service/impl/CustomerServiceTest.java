package com.itacademy.jd2.pk.hop.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICustomer;

public class CustomerServiceTest extends AbstractTest {
	@Test
	public void testCreated() {
		ICustomer entity = saveNewCustomer();
		ICustomer entityFromDb = customerService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getName());
		assertNotNull(entityFromDb.getSurname());
		assertNotNull(entityFromDb.getCityId());
		// assertNotNull(entityFromDb.getPhone()); can be null
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getName(), entityFromDb.getName());
		assertEquals(entity.getSurname(), entityFromDb.getSurname());
		assertEquals(entity.getCityId(), entityFromDb.getCityId());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

		// have null pointer exception

	}

}
