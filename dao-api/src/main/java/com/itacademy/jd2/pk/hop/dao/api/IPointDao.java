package com.itacademy.jd2.pk.hop.dao.api;

import java.util.List;

import com.itacademy.jd2.pk.hop.dao.api.entity.IPoint;

public interface IPointDao extends IDao<IPoint, Integer> {

	void insertList(List<IPoint> entities);

	List<IPoint> selectById(Integer id);
}
