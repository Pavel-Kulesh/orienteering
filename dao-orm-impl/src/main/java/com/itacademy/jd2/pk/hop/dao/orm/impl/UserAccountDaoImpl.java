package com.itacademy.jd2.pk.hop.dao.orm.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.pk.hop.dao.api.IUserAccountDao;
import com.itacademy.jd2.pk.hop.dao.api.entity.IUserAccount;
import com.itacademy.jd2.pk.hop.dao.orm.impl.entity.UserAccount;
import com.itacademy.jd2.pk.hop.dao.orm.impl.entity.UserAccount_;

@Repository
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

		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IUserAccount> cq = cb.createQuery(IUserAccount.class);
		final Root<UserAccount> from = cq.from(UserAccount.class);
		cq.select(from);

		from.fetch(UserAccount_.customer, JoinType.LEFT);
	//	from.fetch(UserAccount_.role, JoinType.LEFT);

		cq.where(cb.equal(from.get(UserAccount_.email), email));

		final TypedQuery<IUserAccount> q = em.createQuery(cq);

		return getSingleResult(q);
	}

}
