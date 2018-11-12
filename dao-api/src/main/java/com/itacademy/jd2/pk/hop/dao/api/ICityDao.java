package com.itacademy.jd2.pk.hop.dao.api;

import java.util.List;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICity;

public interface ICityDao extends IDao<ICity, Integer> {
	List<ICity> getByCountry(Integer id);
}
