package com.itacademy.jd2.pk.hop.dao.orm.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import com.itacademy.jd2.pk.hop.dao.api.IEventDao;
import com.itacademy.jd2.pk.hop.dao.api.entity.IEvent;
import com.itacademy.jd2.pk.hop.dao.api.filter.EventFilter;
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
		// throw new RuntimeException("not implemented");
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IEvent> cq = cb.createQuery(IEvent.class);
		final Root<Event> from = cq.from(Event.class);
		cq.select(from);

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
		throw new RuntimeException("not implemented");
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
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

}
