package com.itacademy.jd2.pk.hop.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICustomer;
import com.itacademy.jd2.pk.hop.dao.api.entity.IUserAccount;

public class RegisterTest extends AbstractTest {
	@Test
	public void testCreated() {
		ICustomer customer = registerData();
		ICustomer customerDB = customerService.get(customer.getId());
		IUserAccount userAccountDB = userAccountService.get(customer.getId());
		IUserAccount userAccount = customer.getUserAccount();

		assertNotNull(customerDB);
		assertNotNull(userAccountDB);

		assertNotNull(customerDB.getId());
		assertNotNull(customerDB.getName());
		assertNotNull(customerDB.getSurname());
		assertNotNull(customerDB.getCity());
		// assertNotNull(entityFromDb.getPhone()); can be null
		assertNotNull(customerDB.getCreated());
		assertNotNull(customerDB.getUpdated());
		assertEquals(customer.getId(), customerDB.getId());
		assertEquals(customer.getName(), customerDB.getName());
		assertEquals(customer.getSurname(), customerDB.getSurname());
		assertEquals(customer.getCity().getId(), customerDB.getCity().getId());

		assertNotNull(userAccountDB.getId());
		assertNotNull(userAccountDB.getEmail());
		assertNotNull(userAccountDB.getPassword());
		assertNotNull(userAccountDB.getCreated());
		assertNotNull(userAccountDB.getUpdated());
		assertEquals(userAccount.getId(), userAccountDB.getId());
		assertEquals(userAccount.getEmail(), userAccountDB.getEmail());
		assertEquals(userAccount.getPassword(), userAccountDB.getPassword());
		assertTrue(userAccount.getCreated().equals(userAccountDB.getUpdated()));

	}

}
