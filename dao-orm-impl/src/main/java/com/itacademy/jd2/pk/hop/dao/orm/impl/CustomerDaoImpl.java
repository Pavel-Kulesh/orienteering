package com.itacademy.jd2.pk.hop.dao.orm.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import com.itacademy.jd2.pk.hop.dao.api.ICustomerDao;
import com.itacademy.jd2.pk.hop.dao.api.entity.ICustomer;
import com.itacademy.jd2.pk.hop.dao.api.filter.CustomerFilter;
import com.itacademy.jd2.pk.hop.dao.orm.impl.entity.Customer;
import com.itacademy.jd2.pk.hop.dao.orm.impl.entity.Customer_;

@Repository
public class CustomerDaoImpl extends AbstractDaoImpl<ICustomer, Integer> implements ICustomerDao {

	protected CustomerDaoImpl() {
		super(Customer.class);

	}

	@Override
	public ICustomer createEntity() {
		Customer customer = new Customer();
		return customer;
	}

	@Override
	public List<ICustomer> find(CustomerFilter filter) {
		// throw new RuntimeException("not implemented");

		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<ICustomer> cq = cb.createQuery(ICustomer.class);
		final Root<Customer> from = cq.from(Customer.class);
		cq.select(from);
		from.fetch(Customer_.userAccount, JoinType.LEFT);
		from.fetch(Customer_.city, JoinType.LEFT);
		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<ICustomer> q = em.createQuery(cq);

		setPaging(filter, q);
		return q.getResultList();

	}

	@Override
	public long getCount(CustomerFilter filter) {
		// throw new RuntimeException("not implemented");

		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<Customer> from = cq.from(Customer.class);
		cq.select(cb.count(from));
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public List<ICustomer> getCustomersByEvent(Integer id) {
		throw new RuntimeException("not implemented");
	}

	private Path<?> getSortPath(final Root<Customer> from, final String sortColumn) {
		switch (sortColumn) {
		case "name":
			return from.get(Customer_.name);
		case "created":
			return from.get(Customer_.created);
		case "updated":
			return from.get(Customer_.updated);
		case "id":
			return from.get(Customer_.id);
		case "surname":
			return from.get(Customer_.surname);
		case "phone":
			return from.get(Customer_.phone);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

}
