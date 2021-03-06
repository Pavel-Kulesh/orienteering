package com.itacademy.jd2.pk.hop.dao.jdbc.impl.entity;

import java.util.Date;
import java.util.Set;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICountry;
import com.itacademy.jd2.pk.hop.dao.api.entity.ICustomer;
import com.itacademy.jd2.pk.hop.dao.api.entity.IEvent;
import com.itacademy.jd2.pk.hop.dao.api.entity.TypeEvent;

public class Event extends BaseEntity implements IEvent {
	private String name;
	private ICustomer customer;
	private Date date;
	private ICountry country;
	private TypeEvent type;
	private String info;
	private Double latitude;
	private Double longitude;
	private Integer version;

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public TypeEvent getType() {
		return type;
	}

	public void setType(TypeEvent type) {
		this.type = type;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public ICountry getCountry() {
		return country;
	}

	public void setCountry(ICountry country) {
		this.country = country;
	}

	public ICustomer getCustomer() {
		return customer;
	}

	public void setCustomer(ICustomer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Event [name=" + name + ", customer=" + customer + ", date=" + date + ", country=" + country + ", type="
				+ type + ", info=" + info + ", latitude=" + latitude + ", longitude=" + longitude + ", getId()="
				+ getId() + ", getCreated()=" + getCreated() + ", getUpdated()=" + getUpdated() + "]";
	}

	@Override
	public Set<ICustomer> getCustomersList() {
		throw new RuntimeException("not implemented");
	}

	@Override
	public void setCustomersList(Set<ICustomer> customersList) {
		throw new RuntimeException("not implemented");
	}

	@Override
	public Integer getVersion() {
		return version;
	}

	@Override
	public void setVersion(Integer version) {
		this.version = version;

	}

}
