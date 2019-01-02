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

import com.itacademy.jd2.pk.hop.dao.api.INewsDao;
import com.itacademy.jd2.pk.hop.dao.api.entity.INews;
import com.itacademy.jd2.pk.hop.dao.api.filter.NewsFilter;
import com.itacademy.jd2.pk.hop.dao.orm.impl.entity.News;
import com.itacademy.jd2.pk.hop.dao.orm.impl.entity.News_;

@Repository
public class NewsDaoImpl extends AbstractDaoImpl<INews, Integer> implements INewsDao {

	protected NewsDaoImpl() {
		super(News.class);

	}

	@Override
	public INews createEntity() {
		News news = new News();
		return news;
	}

	@Override
	public List<INews> find(NewsFilter filter) {
		// throw new RuntimeException("not implemented");

		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<INews> cq = cb.createQuery(INews.class);
		final Root<News> from = cq.from(News.class);
		cq.select(from);

		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<INews> q = em.createQuery(cq);

		setPaging(filter, q);
		return q.getResultList();

	}

	@Override
	public long getCount(NewsFilter filter) {
		// throw new RuntimeException("not implemented");

		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<News> from = cq.from(News.class);
		cq.select(cb.count(from));
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	private Path<?> getSortPath(final Root<News> from, final String sortColumn) {
		switch (sortColumn) {
		case "name":
			return from.get(News_.name);
		case "created":
			return from.get(News_.created);
		case "updated":
			return from.get(News_.updated);
		case "id":
			return from.get(News_.id);
		case "info":
			return from.get(News_.info);

		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

}
