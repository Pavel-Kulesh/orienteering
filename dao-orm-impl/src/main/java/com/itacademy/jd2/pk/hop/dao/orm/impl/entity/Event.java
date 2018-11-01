package com.itacademy.jd2.pk.hop.dao.orm.impl.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICountry;
import com.itacademy.jd2.pk.hop.dao.api.entity.ICustomer;
import com.itacademy.jd2.pk.hop.dao.api.entity.IEvent;
import com.itacademy.jd2.pk.hop.dao.api.entity.Type;

@Entity
public class Event extends BaseEntity implements IEvent {

	@Column
	private String name;
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Customer.class)
	private ICustomer customer;
	@Column
	private Date date;
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Country.class)
	private ICountry country;
	@Column
	@Enumerated(EnumType.STRING)
	private Type type;
	@Column
	private String info;
	@Column
	private Double latitude;
	@Column
	private Double longitude;

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

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
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

}