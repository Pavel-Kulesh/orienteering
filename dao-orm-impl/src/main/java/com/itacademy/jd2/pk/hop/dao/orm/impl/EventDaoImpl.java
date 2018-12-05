package com.itacademy.jd2.pk.hop.dao.orm.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import com.itacademy.jd2.pk.hop.dao.api.IEventDao;
import com.itacademy.jd2.pk.hop.dao.api.entity.ICustomer;
import com.itacademy.jd2.pk.hop.dao.api.entity.IEvent;
import com.itacademy.jd2.pk.hop.dao.api.filter.EventFilter;
import com.itacademy.jd2.pk.hop.dao.orm.impl.entity.Customer;
import com.itacademy.jd2.pk.hop.dao.orm.impl.entity.Customer_;
import com.itacademy.jd2.pk.hop.dao.orm.impl.entity.Event;
import com.itacademy.jd2.pk.hop.dao.orm.impl.entity.Event_;

@Repository
public class EventDaoImpl extends AbstractDaoImpl<IEvent, Integer> implements IEventDao {

	protected EventDaoImpl() {
		super(Event.class);
	}

	@Override
	public IEvent createEntity() {
		IEvent event = new Event();
		return event;
	}

	@Override
	public List<IEvent> find(EventFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IEvent> cq = cb.createQuery(IEvent.class);
		final Root<Event> from = cq.from(Event.class);
		cq.select(from);
		from.fetch(Event_.customer, JoinType.LEFT);
		from.fetch(Event_.country, JoinType.LEFT);

		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<IEvent> q = em.createQuery(cq);

		setPaging(filter, q);
		return q.getResultList();

	}

	@Override
	public long getCount(EventFilter filter) {
		// throw new RuntimeException("not implemented");

		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<Event> from = cq.from(Event.class);
		cq.select(cb.count(from));
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();

	}

	@Override
	public List<IEvent> getEventsByCustomer(Integer id) {

		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<ICustomer> cq = cb.createQuery(ICustomer.class);
		final Root<Customer> from = cq.from(Customer.class);
		cq.select(from);
		from.fetch(Customer_.eventsList, JoinType.LEFT);

		cq.where(cb.equal(from.get(Customer_.id), id));
		final TypedQuery<ICustomer> q = em.createQuery(cq);

		List<ICustomer> resultList = q.getResultList();
		Customer customer = (Customer) resultList.get(0);
		Set<IEvent> events = customer.getEventsList();
		List<IEvent> eventsList = new ArrayList<IEvent>();
		eventsList.addAll(events);
		return eventsList;

	}

	private Path<?> getSortPath(final Root<Event> from, final String sortColumn) {
		switch (sortColumn) {
		case "name":
			return from.get(Event_.name);
		case "created":
			return from.get(Event_.created);
		case "updated":
			return from.get(Event_.updated);
		case "id":
			return from.get(Event_.id);
		case "type":
			return from.get(Event_.type);
		case "date":
			return from.get(Event_.date);
		case "country":
			return from.get(Event_.country);
		case "customer_id":
			return from.get(Event_.customer);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public IEvent get(Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IEvent> cq = cb.createQuery(IEvent.class);
		final Root<Event> from = cq.from(Event.class);
		cq.select(from);
		from.fetch(Event_.customer, JoinType.LEFT);
		from.fetch(Event_.country, JoinType.LEFT);

		cq.where(cb.equal(from.get(Event_.id), id));
		final TypedQuery<IEvent> q = em.createQuery(cq);
	
		return q.getResultList().get(0);
	}

	@Override
	public void addCustomerToEvent(Integer customerId, Integer eventId) {
		final EntityManager em = getEntityManager();
		em.createNativeQuery(String.format("insert into customer_2_event (customer_id, event_id) values(%s, %s)",
				customerId, eventId)).executeUpdate();

	}

	@Override
	public void deleteCustomerFromEvent(Integer customerId, Integer eventId) {
		final EntityManager em = getEntityManager();
		em.createNativeQuery((String.format("delete from customer_2_event e where e.customer_id = %s and e.event_id=%s",
				customerId, eventId))).executeUpdate();

	}

	@Override
	public boolean checkExistCustomerToEvent(Integer customerId, Integer eventId) {
		final EntityManager em = getEntityManager();

		@SuppressWarnings("unchecked")
		List<ICustomer> resRequest = em.createNativeQuery(String.format(
				"select * from customer a1 join customer_2_event b1 on a1.id=b1.customer_id where a1.id=%s and event_id=%s",
				customerId, eventId)).getResultList();
		if (!resRequest.isEmpty()) {
			return true;
		}
		return false;
	}

}
