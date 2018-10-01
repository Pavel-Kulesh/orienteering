package com.itacademy.jd2.pk.hop.dao.api.entity;

public interface IRoute extends IBaseEntity {
	String getName();

	void setName(String name);

	String getPath();

	void setPath(String path);

	Integer getUserId();

	void setUserId(Integer userId);
}
