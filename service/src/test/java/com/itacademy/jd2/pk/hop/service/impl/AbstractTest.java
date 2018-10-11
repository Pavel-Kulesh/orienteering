package com.itacademy.jd2.pk.hop.service.impl;

import java.util.Date;
import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICity;
import com.itacademy.jd2.pk.hop.dao.api.entity.ICountry;
import com.itacademy.jd2.pk.hop.dao.api.entity.ICustomer;
import com.itacademy.jd2.pk.hop.dao.api.entity.INews;
import com.itacademy.jd2.pk.hop.dao.api.entity.IPoint;
import com.itacademy.jd2.pk.hop.dao.api.entity.IRoute;
import com.itacademy.jd2.pk.hop.dao.api.entity.IUserAccount;
import com.itacademy.jd2.pk.hop.dao.api.entity.Role;
import com.itacademy.jd2.pk.hop.service.ICityService;
import com.itacademy.jd2.pk.hop.service.ICountryService;
import com.itacademy.jd2.pk.hop.service.ICustomerService;
import com.itacademy.jd2.pk.hop.service.INewsServise;
import com.itacademy.jd2.pk.hop.service.IPointService;
import com.itacademy.jd2.pk.hop.service.IRouteService;
import com.itacademy.jd2.pk.hop.service.IUserAccountService;

@SpringJUnitConfig(locations = "classpath:service-context-test.xml")
@Sql(scripts = "classpath:clean-db.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
public class AbstractTest {
	@Autowired
	protected ICountryService countryService;
	@Autowired
	protected ICityService cityService;
	@Autowired
	protected INewsServise newsService;
	@Autowired
	protected IPointService pointService;
	@Autowired
	protected IUserAccountService userAccountService;
	@Autowired
	protected ICustomerService customerService;
	@Autowired
	protected IRouteService routeService;

	// +event

	// +map

	private static Random RANDOM = new Random();

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractTest.class);

	@AfterAll
	public static void cleadDB() {
		LOGGER.info("----------------clean DB---------------------");
	}

	protected String getRandomPrefix() {
		return RANDOM.nextInt(99999) + "";

	}

	protected int getRandomObjectsCount() {
		return RANDOM.nextInt(9) + 1;
	}

	public Random getRANDOM() {
		return RANDOM;
	}

	protected ICountry saveNewCountry() {
		ICountry entity = countryService.createEntity();
		entity.setName("country-" + getRandomPrefix());
		countryService.save(entity);
		return entity;
	}

	protected ICity saveNewCity() {

		ICity entity = cityService.createEntity();
		entity.setName("city-" + getRandomPrefix());
		ICountry country = saveNewCountry();
		entity.setCountryId(country.getId());

		cityService.save(entity);
		return entity;
	}

	protected INews saveNewNews() {
		INews entity = newsService.createEntity();
		entity.setName("news-" + getRandomPrefix());
		entity.setInfo("info" + getRandomPrefix());

		newsService.save(entity);
		return entity;
	}

	protected IUserAccount saveNewUser() {
		IUserAccount entity = userAccountService.createEntity();
		entity.setEmail("email-" + getRandomPrefix());
		entity.setPassword("password-" + getRandomPrefix());

		Role[] allRoles = Role.values();
		int randomIndex = Math.max(0, getRANDOM().nextInt(allRoles.length));
		entity.setRole(allRoles[randomIndex]);

		userAccountService.save(entity);

		return entity;

	}

	protected ICustomer saveNewCustomer() {
		ICustomer entity = customerService.createEntity();

		IUserAccount user = saveNewUser();
		ICity city = saveNewCity();

		entity.setId(user.getId());
		entity.setName("name-" + getRandomPrefix());
		entity.setSurname("surname-" + getRandomPrefix());
		entity.setPhone("phone-" + getRandomPrefix());
		entity.setCityId(city.getId());
		customerService.save(entity);

		return entity;

	}

	protected IRoute saveNewRoute() {
		IRoute entity = routeService.createEntity();
		ICustomer customer = saveNewCustomer();

		entity.setName("name-" + getRandomPrefix());
		entity.setPath("path-" + getRandomPrefix());
		entity.setFile("file-" + getRandomPrefix());
		entity.setUserId(customer.getId());
		routeService.save(entity);

		return entity;

	}

	protected IPoint saveNewPoint() {
		IPoint entity = pointService.createEntity();
		IRoute route = saveNewRoute();
		entity.setRouteId(route.getId());

		double x = Math.random();

		x = (Math.floor(x * 1_000_000_000)) / 1_000_000_000;
		entity.setLatitude(x + getRandomObjectsCount());
		entity.setLongitude(x + getRandomObjectsCount());
		entity.setCreated(new Date());
		entity.setUpdated(new Date());
		entity.setDiffTime(getRandomObjectsCount());

		pointService.save(entity);

		return entity;
	}

}
