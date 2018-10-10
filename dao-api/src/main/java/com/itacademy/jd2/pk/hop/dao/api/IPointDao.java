package com.itacademy.jd2.pk.hop.dao.api;

import com.itacademy.jd2.pk.hop.dao.api.entity.IPoint;

public interface IPointDao extends IDao<IPoint, Integer> {

	void insertList(IPoint... entities);

}
