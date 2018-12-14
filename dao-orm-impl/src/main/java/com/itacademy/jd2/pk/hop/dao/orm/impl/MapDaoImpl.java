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

import com.itacademy.jd2.pk.hop.dao.api.IMapDao;
import com.itacademy.jd2.pk.hop.dao.api.entity.IMap;
import com.itacademy.jd2.pk.hop.dao.api.entity.IRoute;
import com.itacademy.jd2.pk.hop.dao.api.filter.MapFilter;
import com.itacademy.jd2.pk.hop.dao.orm.impl.entity.Map;
import com.itacademy.jd2.pk.hop.dao.orm.impl.entity.Map_;

@Repository
public class MapDaoImpl extends AbstractDaoImpl<IMap, Integer> implements IMapDao {

	protected MapDaoImpl() {
		super(Map.class);
	}

	@Override
	public IMap createEntity() {
		Map map = new Map();
		return map;
	}

	@Override
	public List<IMap> find(MapFilter filter) {
		// throw new RuntimeException("not implemented");

		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IMap> cq = cb.createQuery(IMap.class);
		final Root<Map> from = cq.from(Map.class);
		cq.select(from);
		from.fetch(Map_.customer, JoinType.LEFT);
		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<IMap> q = em.createQuery(cq);

		setPaging(filter, q);
		return q.getResultList();

	}

	@Override
	public long getCount(MapFilter filter) {
		// throw new RuntimeException("not implemented");

		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<Map> from = cq.from(Map.class);
		cq.select(cb.count(from));
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	private Path<?> getSortPath(final Root<Map> from, final String sortColumn) {
		switch (sortColumn) {
		case "name":
			return from.get(Map_.name);
		case "created":
			return from.get(Map_.created);
		case "updated":
			return from.get(Map_.updated);
		case "id":
			return from.get(Map_.id);
		case "customer":
			return from.get(Map_.customer);

		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public IMap get(Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IMap> cq = cb.createQuery(IMap.class);
		final Root<Map> from = cq.from(Map.class);
		cq.select(from);
		from.fetch(Map_.customer, JoinType.LEFT);
		cq.where(cb.equal(from.get(Map_.id), id));
		final TypedQuery<IMap> q = em.createQuery(cq);

		List<IMap> resultList = q.getResultList();
		return resultList.isEmpty() ? null : resultList.get(0);
	}

	@Override
	public void addRouteToEvent(Integer mapId, Integer routeId) {
		final EntityManager em = getEntityManager();
		em.createNativeQuery(String.format("insert into map_2_route (map_id, route_id) values(%s, %s)", mapId, routeId))
				.executeUpdate();

	}

	@Override
	public void deleteRouteFromEvent(Integer mapId, Integer routeId) {
		final EntityManager em = getEntityManager();
		em.createNativeQuery(
				(String.format("delete from map_2_route e where e.map_id = %s and e.route_id=%s", mapId, routeId)))
				.executeUpdate();

	}

	@Override
	public List<IRoute> getRoutesOnMap(Integer mapId) {

		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IMap> cq = cb.createQuery(IMap.class);
		final Root<Map> from = cq.from(Map.class);
		cq.select(from);
		from.fetch(Map_.routesList, JoinType.LEFT);

		cq.where(cb.equal(from.get(Map_.id), mapId));
		final TypedQuery<IMap> q = em.createQuery(cq);

		List<IMap> resultList = q.getResultList();
		Map route = (Map) resultList.get(0);
		Set<IRoute> routes = route.getRoutesList();
		List<IRoute> routesList = new ArrayList<IRoute>();
		routesList.addAll(routes);
		return routesList;
	}

}
