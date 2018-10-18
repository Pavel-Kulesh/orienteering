package com.itacademy.jd2.pk.hop.dao.api;

import java.util.List;

import com.itacademy.jd2.pk.hop.dao.api.entity.INews;
import com.itacademy.jd2.pk.hop.dao.api.filter.NewsFilter;

public interface INewsDao extends IDao<INews, Integer> {
	List<INews> find(NewsFilter filter);

	long getCount(NewsFilter filter);
}
