package com.itacademy.jd2.pk.hop.dao.orm.impl;

import com.itacademy.jd2.pk.hop.dao.api.IUserAccountDao;
import com.itacademy.jd2.pk.hop.dao.api.entity.IUserAccount;
import com.itacademy.jd2.pk.hop.dao.orm.impl.entity.UserAccount;

public class UserAccountDaoImpl extends AbstractDaoImpl<IUserAccount, Integer> implements IUserAccountDao {

	protected UserAccountDaoImpl() {
		super(UserAccount.class);

	}

	@Override
	public IUserAccount createEntity() {
		UserAccount userAccount = new UserAccount();
		return userAccount;
	}

	@Override
	public IUserAccount getByEmail(String email) {

		throw new RuntimeException("not implemented");
	}

}
