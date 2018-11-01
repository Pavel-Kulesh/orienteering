package com.itacademy.jd2.pk.hop.dao.orm.impl;

import java.util.List;

import com.itacademy.jd2.pk.hop.dao.api.INewsDao;
import com.itacademy.jd2.pk.hop.dao.api.entity.INews;
import com.itacademy.jd2.pk.hop.dao.api.filter.NewsFilter;
import com.itacademy.jd2.pk.hop.dao.orm.impl.entity.News;


public class NewsDaoImpl extends AbstractDaoImpl<INews, Integer> implements INewsDao {

	protected NewsDaoImpl() {
		super(News.class);

	}

	@Override
	public INews createEntity() {
		News news = new News();
		return news;
	}

	@Override
	public List<INews> find(NewsFilter filter) {
		throw new RuntimeException("not implemented");
	}

	@Override
	public long getCount(NewsFilter filter) {
		throw new RuntimeException("not implemented");
	}



}
