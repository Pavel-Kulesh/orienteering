package com.itacademy.jd2.pk.hop.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.pk.hop.dao.api.entity.IMap;
import com.itacademy.jd2.pk.hop.dao.api.entity.IRoute;
import com.itacademy.jd2.pk.hop.dao.api.entity.Track;
import com.itacademy.jd2.pk.hop.dao.api.filter.MapFilter;

public interface IMapService {
	IMap get(Integer id);

	List<IMap> getAll();
	@Transactional
	void save(IMap entity);
	@Transactional
	void delete(Integer id);
	@Transactional
	void deleteAll();

	IMap createEntity();

	List<IMap> find(MapFilter filter);

	long getCount(MapFilter filter);
	
	@Transactional
	void addRouteToMap(Integer mapId, Integer routeId);

	@Transactional
	void deleteRouteFromMap(Integer mapId, Integer routeId);
	
	List<IRoute> getRoutesOnMap(Integer mapId, Track track);

	List<IRoute> getRoutesOnMapByCustomer(Integer mapId,Integer customerId);
	
	
	
}
