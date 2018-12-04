package com.itacademy.jd2.pk.hop.dao.orm.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.pk.hop.dao.api.IPointDao;
import com.itacademy.jd2.pk.hop.dao.api.entity.IPoint;
import com.itacademy.jd2.pk.hop.dao.orm.impl.entity.Point;
import com.itacademy.jd2.pk.hop.dao.orm.impl.entity.Point_;
import com.itacademy.jd2.pk.hop.dao.orm.impl.entity.Route_;

@Repository
public class PointDaoImpl extends AbstractDaoImpl<IPoint, Integer> implements IPointDao {

	protected PointDaoImpl() {
		super(Point.class);
	}

	@Override
	public IPoint createEntity() {
		Point point = new Point();
		return point;
	}

	@Override
	public void insertList(List<IPoint> entities) {
		final EntityManager em = getEntityManager();

		for (IPoint point : entities) {
			em.persist(point);
		}

	}

	@Override
	public List<IPoint> selectById(Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IPoint> cq = cb.createQuery(IPoint.class);
		final Root<Point> from = cq.from(Point.class);
		cq.select(from);

		from.fetch(Point_.route, JoinType.LEFT);

		cq.where(cb.equal(from.get(Point_.route).get(Route_.id), id));
		final TypedQuery<IPoint> q = em.createQuery(cq);
		List<IPoint> result = q.getResultList();

		return result;

	}

	@Override
	public void delete(Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaDelete<Point> delete = cb.createCriteriaDelete(Point.class);

		Root<Point> from = delete.from(Point.class);

		delete.where(cb.equal(from.get(Point_.route).get(Route_.id), id));

		em.createQuery(delete).executeUpdate();

	}

}
