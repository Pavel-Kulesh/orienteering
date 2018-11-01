package com.itacademy.jd2.pk.hop.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import javax.swing.text.html.parser.Entity;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICity;
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
		assertNotNull(entityFromDb.getCity());
		// assertNotNull(entityFromDb.getPhone()); can be null
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getId(), entityFromDb.getId());
		assertEquals(entity.getName(), entityFromDb.getName());
		assertEquals(entity.getSurname(), entityFromDb.getSurname());
		assertEquals(entity.getCity().getId(), entityFromDb.getCity().getId());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

	}

	@Test
	public void testUpdate() throws InterruptedException {
		ICustomer entity = saveNewCustomer();
		ICity newCity = saveNewCity();

		String newName = entity.getName() + "_updated";
		String newSurname = entity.getSurname() + "_updated";
		String newPhone = null;
		entity.setName(newName);
		entity.setSurname(newSurname);
		entity.setPhone(newPhone);

		entity.setCity(newCity);

		Thread.sleep(1000);
		customerService.update(entity);

		ICustomer entityFromDb = customerService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getName());
		assertNotNull(entityFromDb.getSurname());
		assertNotNull(entityFromDb.getCity());
		// assertNotNull(entityFromDb.getPhone()); can be null

		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getId(), entityFromDb.getId());
		assertEquals(newName, entityFromDb.getName());
		assertEquals(newSurname, entityFromDb.getSurname());
		assertEquals(newPhone, entityFromDb.getPhone());
		assertEquals(newCity.getId(), entityFromDb.getCity().getId());

		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));
	}

	@Test
	public void testGetAll() {
		int initialCount = customerService.getAll().size();
		int randomObjectsCount = getRandomObjectsCount();

		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewCustomer();
		}
		List<ICustomer> allEntities = customerService.getAll();

		for (ICustomer entityFromDb : allEntities) {
			assertNotNull(entityFromDb);
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getName());
			assertNotNull(entityFromDb.getSurname());
			// assertNotNull(entityFromDb.getPhone()); can be null

			assertNotNull(entityFromDb.getCity());

			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}
		assertEquals(randomObjectsCount + initialCount, allEntities.size());

	}

	@Test
	public void testDeleteById() {
		ICustomer entity = saveNewCustomer();
		customerService.delete(entity.getId());
		assertNull(customerService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewCustomer();
		customerService.deleteAll();
		assertEquals(0, customerService.getAll().size());
	}

}
