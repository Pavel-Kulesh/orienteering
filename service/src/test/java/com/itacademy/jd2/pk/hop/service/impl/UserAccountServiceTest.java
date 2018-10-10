package com.itacademy.jd2.pk.hop.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.pk.hop.dao.api.entity.IUserAccount;
import com.itacademy.jd2.pk.hop.dao.api.entity.Role;

public class UserAccountServiceTest extends AbstractTest {
	@Test
	public void testCreated() {
		IUserAccount entity = saveNewUser();
		IUserAccount entityFromDb = userAccountService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getEmail());
		assertNotNull(entityFromDb.getPassword());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getId(), entityFromDb.getId());
		assertEquals(entity.getEmail(), entityFromDb.getEmail());
		assertEquals(entity.getPassword(), entityFromDb.getPassword());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

	}

	@Test
	public void testUpdate() throws InterruptedException {
		IUserAccount entity = saveNewUser();
		String newPass = entity.getPassword() + "_updated";

		entity.setPassword(newPass);

		Role[] allRoles = Role.values();
		int randomIndex = Math.max(0, getRANDOM().nextInt(allRoles.length) - 1);
		Role newRole = allRoles[randomIndex];
		entity.setRole(newRole);

		Thread.sleep(1000);
		userAccountService.save(entity);

		IUserAccount entityFromDb = userAccountService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getEmail());
		assertNotNull(entityFromDb.getPassword());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getId(), entityFromDb.getId());
		assertEquals(newPass, entityFromDb.getPassword());
		assertEquals(newRole, entityFromDb.getRole());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));

	}

	@Test
	public void testGetAll() {
		int initialCount = userAccountService.getAll().size();
		int randomObjectsCount = getRandomObjectsCount();

		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewUser();
		}

		List<IUserAccount> allEntities = userAccountService.getAll();

		for (IUserAccount entityFromDb : allEntities) {
			assertNotNull(entityFromDb);
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getEmail());
			assertNotNull(entityFromDb.getPassword());
			assertNotNull(entityFromDb.getRole());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}
		assertEquals(randomObjectsCount + initialCount, allEntities.size());

	}

	@Test
	public void testDeleteById() {
		IUserAccount entity = saveNewUser();
		userAccountService.delete(entity.getId());
		assertNull(userAccountService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewUser();
		userAccountService.deleteAll();
		assertEquals(0, userAccountService.getAll().size());
	}

}
