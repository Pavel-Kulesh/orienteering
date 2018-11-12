package com.itacademy.jd2.pk.hop.dao.orm.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.pk.hop.dao.api.ICityDao;
import com.itacademy.jd2.pk.hop.dao.api.entity.ICity;
import com.itacademy.jd2.pk.hop.dao.orm.impl.entity.City;
import com.itacademy.jd2.pk.hop.dao.orm.impl.entity.City_;
import com.itacademy.jd2.pk.hop.dao.orm.impl.entity.Country_;

@Repository
public class CityDaoImpl extends AbstractDaoImpl<ICity, Integer> implements ICityDao {

	protected CityDaoImpl() {
		super(City.class);
	}

	@Override
	public ICity createEntity() {
		City city = new City();
		return city;
	}

	public List<ICity> getByCountry(Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<ICity> cq = cb.createQuery(ICity.class);
		final Root<City> from = cq.from(City.class);
		cq.select(from);
		from.fetch(City_.country, JoinType.LEFT);

		cq.where(cb.equal(from.get(City_.country).get(Country_.id), id));
		final TypedQuery<ICity> q = em.createQuery(cq);
		return q.getResultList();

	}
}
