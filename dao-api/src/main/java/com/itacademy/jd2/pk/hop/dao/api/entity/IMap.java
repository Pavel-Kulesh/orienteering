package com.itacademy.jd2.pk.hop.dao.api.entity;

import java.math.BigDecimal;

public interface IMap extends IBaseEntity {
	String getName();

	void setName(String name);

	String getPath();

	void setPath(String path);

	String getFile();

	void setFile(String file);

	BigDecimal getLatitude1();

	void setLatitude1(BigDecimal latitude1);

	BigDecimal getLatitude2();

	void setLatitude2(BigDecimal latitude2);

	BigDecimal getLongitude1();

	void setLongitude1(BigDecimal longitude1);

	BigDecimal getLongitude2();

	void setLongitude2(BigDecimal longitude2);

	ICustomer getCustomer();

	void setCustomer(ICustomer customer);

	byte[] getImage();

	void setImage(byte[] image);

}
