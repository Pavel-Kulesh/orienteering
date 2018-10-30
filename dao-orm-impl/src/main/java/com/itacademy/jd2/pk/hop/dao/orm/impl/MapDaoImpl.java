package com.itacademy.jd2.pk.hop.dao.orm.impl;

import java.util.List;

import com.itacademy.jd2.pk.hop.dao.api.IMapDao;
import com.itacademy.jd2.pk.hop.dao.api.entity.IMap;
import com.itacademy.jd2.pk.hop.dao.api.filter.MapFilter;
import com.itacademy.jd2.pk.hop.dao.orm.impl.entity.Map;

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
		throw new RuntimeException("not implemented");
	}

	@Override
	public long getCount(MapFilter filter) {
		throw new RuntimeException("not implemented");
	}

}
