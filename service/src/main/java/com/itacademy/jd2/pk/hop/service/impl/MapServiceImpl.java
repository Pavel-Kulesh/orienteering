package com.itacademy.jd2.pk.hop.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.pk.hop.dao.api.IMapDao;
import com.itacademy.jd2.pk.hop.dao.api.IRouteDao;
import com.itacademy.jd2.pk.hop.dao.api.entity.IMap;
import com.itacademy.jd2.pk.hop.dao.api.entity.IRoute;
import com.itacademy.jd2.pk.hop.dao.api.filter.MapFilter;
import com.itacademy.jd2.pk.hop.service.IMapService;

@Service
public class MapServiceImpl implements IMapService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MapServiceImpl.class);

	private IMapDao dao;
	private IRouteDao routeDao;

	@Autowired
	public MapServiceImpl(IMapDao dao, IRouteDao routeDao) {
		super();
		this.dao = dao;
		this.routeDao = routeDao;
	}

	@Override
	public IMap get(Integer id) {
		IMap entity = dao.get(id);
		return entity;
	}

	@Override
	public List<IMap> getAll() {
		List<IMap> all = dao.selectAll();
		return all;
	}

	@Override
	public void save(IMap entity) {
		Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			dao.insert(entity);
			LOGGER.info("new map created: {}", entity);
		} else {
			dao.update(entity);
			LOGGER.debug("map updated: {}", entity);

		}
	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		dao.deleteAll();
	}

	@Override
	public IMap createEntity() {
		return dao.createEntity();
	}

	@Override
	public List<IMap> find(MapFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(MapFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public void addRouteToMap(Integer mapId, Integer routeId) {
		dao.addRouteToEvent(mapId, routeId);

	}

	@Override
	public void deleteRouteFromMap(Integer mapId, Integer routeId) {
		dao.deleteRouteFromEvent(mapId, routeId);

	}

	@Override
	public List<IRoute> getRoutesOnMap(Integer mapId) {

		return dao.getRoutesOnMap(mapId);
	}

	@Override
	public List<IRoute> getRoutesOnMapByCustomer(Integer mapId, Integer customerId) {

		List<IRoute> routesOnMapByCustomer = dao.getRoutesOnMap(mapId);

		List<IRoute> result = new ArrayList<>();
		for (IRoute route : routesOnMapByCustomer) {
			IRoute res = routeDao.get(route.getId());
			if (res.getCustomer().getId().equals(customerId)) {

				result.add(res);
			}
		}

		return result;
	}

}
