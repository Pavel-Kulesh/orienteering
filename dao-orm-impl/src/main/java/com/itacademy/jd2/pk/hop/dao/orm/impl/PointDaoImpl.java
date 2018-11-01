package com.itacademy.jd2.pk.hop.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.pk.hop.dao.api.IPointDao;
import com.itacademy.jd2.pk.hop.dao.api.entity.IPoint;
import com.itacademy.jd2.pk.hop.dao.orm.impl.entity.Point;
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
		throw new RuntimeException("not implemented");

	}

}
