package com.itacademy.jd2.pk.hop.dao.api.entity;

public interface IMap extends IBaseEntity {
	String getName();

	void setName(String name);

	String getPath();

	void setPath(String path);

	String getFile();

	void setFile(String file);

	Integer getUserId();

	void setUserId(Integer userId);

	Double getLatitude1();

	void setLatitude1(Double latitude1);

	Double getLatitude2();

	void setLatitude2(Double latitude2);

	Double getLongitude1();

	void setLongitude1(Double longitude1);

	Double getLongitude2();

	void setLongitude2(Double longitude2);
}
