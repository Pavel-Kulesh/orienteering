package com.itacademy.jd2.pk.hop.dao.orm.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import org.hibernate.jpa.criteria.OrderImpl;

import com.itacademy.jd2.pk.hop.dao.api.INewsDao;
import com.itacademy.jd2.pk.hop.dao.api.entity.INews;
import com.itacademy.jd2.pk.hop.dao.api.filter.NewsFilter;
import com.itacademy.jd2.pk.hop.dao.orm.impl.entity.News;
import com.itacademy.jd2.pk.hop.dao.orm.impl.entity.News_;

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
