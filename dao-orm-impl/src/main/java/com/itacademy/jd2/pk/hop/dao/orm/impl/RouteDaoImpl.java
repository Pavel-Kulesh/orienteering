package com.itacademy.jd2.pk.hop.dao.orm.impl;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.pk.hop.dao.api.IRouteDao;
import com.itacademy.jd2.pk.hop.dao.api.entity.IRoute;
import com.itacademy.jd2.pk.hop.dao.orm.impl.entity.Route;
@Repository
public class RouteDaoImpl extends AbstractDaoImpl<IRoute, Integer> implements IRouteDao {

	protected RouteDaoImpl() {
		super(Route.class);
	}

	@Override
	public IRoute createEntity() {
		Route route = new Route();
		return route;
	}

}