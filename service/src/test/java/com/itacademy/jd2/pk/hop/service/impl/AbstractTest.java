package com.itacademy.jd2.pk.hop.service.impl;

import static org.mockito.ArgumentMatchers.anyString;

import java.util.Date;
import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICity;
import com.itacademy.jd2.pk.hop.dao.api.entity.ICountry;
import com.itacademy.jd2.pk.hop.dao.api.entity.ICustomer;
import com.itacademy.jd2.pk.hop.dao.api.entity.IEvent;
import com.itacademy.jd2.pk.hop.dao.api.entity.IMap;
import com.itacademy.jd2.pk.hop.dao.api.entity.INews;
import com.itacademy.jd2.pk.hop.dao.api.entity.IPoint;
import com.itacademy.jd2.pk.hop.dao.api.entity.IRoute;
import com.itacademy.jd2.pk.hop.dao.api.entity.IUserAccount;
import com.itacademy.jd2.pk.hop.dao.api.entity.Role;
import com.itacademy.jd2.pk.hop.dao.api.entity.TypeEvent;
import com.itacademy.jd2.pk.hop.service.ICityService;
import com.itacademy.jd2.pk.hop.service.ICountryService;
import com.itacademy.jd2.pk.hop.service.ICustomerService;
import com.itacademy.jd2.pk.hop.service.IEventService;
import com.itacademy.jd2.pk.hop.service.IMailService;
import com.itacademy.jd2.pk.hop.service.IMapService;
import com.itacademy.jd2.pk.hop.service.INewsServise;
import com.itacademy.jd2.pk.hop.service.IPointService;
import com.itacademy.jd2.pk.hop.service.IRegisterService;
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
	@Autowired
	protected IEventService eventService;
	@Autowired
	protected IMapService mapService;

	@Autowired
	protected IRegisterService registerService;

	@Autowired
	protected IMailService mailService;

	private static Random RANDOM = new Random();

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractTest.class);

	@BeforeEach
	public void setUpMethod() {

		final IMailService spy = Mockito.spy(mailService);
		Mockito.doAnswer(new Answer<Void>() {
			@Override
			public final Void answer(final InvocationOnMock invocation) throws Throwable {
				LOGGER.info("email sending simulated:{}", invocation.getArguments()[0]);
				return null;
			}
		}).when(spy).sendEmail(anyString());
		registerService.setMailServise(spy);

	}

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

	public double getDoubleNumber() {
		double x = Math.random();

		x = (Math.floor(x * 1_000_000_000)) / 1_000_000_000 + getRandomObjectsCount();
		return x;
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

		entity.setCountry(country);
		;

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

	protected IUserAccount saveNewUserAccount() {
		IUserAccount entity = userAccountService.createEntity();
		entity.setEmail("email-" + getRandomPrefix());
		entity.setPassword("password-" + getRandomPrefix());
		ICustomer customer = customerService.createEntity();

		Role[] allRoles = Role.values();
		int randomIndex = Math.max(0, getRANDOM().nextInt(allRoles.length));
		entity.setRole(allRoles[randomIndex]);

		userAccountService.save(entity);
		Integer id = entity.getId();
		customer.setId(id);
		entity.setCustomer(customer);
		return entity;

	}

	protected ICustomer saveNewCustomer() {
		IUserAccount userAccount = saveNewUserAccount();
		ICity city = saveNewCity();
		ICustomer customer = customerService.createEntity();
		customer.setUserAccount(userAccount);
		customer.setName("name-" + getRandomPrefix());
		customer.setSurname("surname-" + getRandomPrefix());
		customer.setPhone("phone-" + getRandomPrefix());
		customer.setCity(city);

		customerService.save(customer);

		return customer;

	}

	protected IRoute saveNewRoute() {
		IRoute entity = routeService.createEntity();
		ICustomer customer = saveNewCustomer();

		entity.setName("name-" + getRandomPrefix());
		entity.setCustomer(customer);

		routeService.save(entity);

		return entity;

	}

	protected IPoint saveNewPoint() {
		IPoint entity = pointService.createEntity();
		IRoute route = saveNewRoute();
		entity.setRoute(route);

		entity.setLatitude(getDoubleNumber());
		entity.setLongitude(getDoubleNumber());
		entity.setCreated(new Date());
		entity.setUpdated(new Date());
//		entity.setDiffTime(getRandomObjectsCount());

		pointService.save(entity);

		return entity;
	}

	protected IEvent saveNewEvent() {
		IEvent entity = eventService.createEntity();
		ICustomer customer = saveNewCustomer();
		ICountry country = saveNewCountry();

		entity.setName("name-" + getRandomPrefix());
		entity.setCustomer(customer);
		entity.setDate(new Date());
		entity.setCountry(country);

		TypeEvent[] allTypes = TypeEvent.values();
		int randomIndex = Math.max(0, getRANDOM().nextInt(allTypes.length));
		entity.setType(allTypes[randomIndex]);

		entity.setInfo("info-" + getRandomPrefix());
		entity.setLatitude(getDoubleNumber());
		entity.setLongitude(getDoubleNumber());

		eventService.save(entity);

		return entity;
	}

	protected IMap saveNewMap() {
		IMap entity = mapService.createEntity();
		ICustomer customer = saveNewCustomer();

		entity.setName("name-" + getRandomPrefix());
		byte[] bytesArray = getRandomPrefix().getBytes();
		entity.setImage(bytesArray);
		entity.setCustomer(customer);

		mapService.save(entity);

		return entity;
	}

	protected ICustomer registerData() {
		IUserAccount entity = userAccountService.createEntity();
		entity.setEmail("email-" + getRandomPrefix());
		entity.setPassword("password-" + getRandomPrefix());
		ICustomer customer = customerService.createEntity();
		ICity city = saveNewCity();
		customer.setUserAccount(entity);
		customer.setName("name-" + getRandomPrefix());
		customer.setSurname("surname-" + getRandomPrefix());
		customer.setPhone("phone-" + getRandomPrefix());
		customer.setCity(city);

		Role[] allRoles = Role.values();
		int randomIndex = Math.max(0, getRANDOM().nextInt(allRoles.length));
		entity.setRole(allRoles[randomIndex]);

		registerService.saveRegisterData(customer, entity);

		Integer id = entity.getId();
		customer.setId(id);
		entity.setCustomer(customer);

		return customer;

	}
}
