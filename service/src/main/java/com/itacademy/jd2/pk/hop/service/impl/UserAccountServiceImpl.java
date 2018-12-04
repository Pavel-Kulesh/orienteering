package com.itacademy.jd2.pk.hop.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.pk.hop.dao.api.IUserAccountDao;
import com.itacademy.jd2.pk.hop.dao.api.entity.IUserAccount;
import com.itacademy.jd2.pk.hop.service.IUserAccountService;

@Service
public class UserAccountServiceImpl implements IUserAccountService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserAccountServiceImpl.class);

	private IUserAccountDao dao;

	@Autowired
	public UserAccountServiceImpl(IUserAccountDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public IUserAccount get(Integer id) {

		IUserAccount entity = dao.get(id);
		return entity;
	}

	@Override
	public List<IUserAccount> getAll() {
		List<IUserAccount> all = dao.selectAll();

		return all;
	}

	@Override
	public void save(IUserAccount entity) {
		Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);

			dao.insert(entity);
			LOGGER.info("new user account created: {}", entity);
		} else {
			LOGGER.debug("user account updated: {}", entity);
			dao.update(entity);
		}

	}

	@Override
	public void delete(Integer id) {
		LOGGER.info("delete user account with id=" + id + "-------------------");
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all user accounts--------------------");
		dao.deleteAll();
	}

	@Override
	public IUserAccount createEntity() {
		return dao.createEntity();
	}

	@Override
	public IUserAccount getByEmail(String email) {

		IUserAccount entity = dao.getByEmail(email);
		return entity;
	}

}
