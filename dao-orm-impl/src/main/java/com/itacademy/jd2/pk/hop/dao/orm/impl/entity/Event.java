package com.itacademy.jd2.pk.hop.dao.orm.impl.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.itacademy.jd2.pk.hop.dao.api.entity.IEvent;
import com.itacademy.jd2.pk.hop.dao.api.entity.Type;
@Entity
public class Event extends BaseEntity implements IEvent {
	@Column
	private String name;
	@Column
	private Integer creatorId;
	@Column
	private Date date;
	@Column
	private Integer countryId;
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

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
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

	@Override
	public String toString() {
		return "Event [name=" + name + ", creatorId=" + creatorId + ", date=" + date + ", countryId=" + countryId
				+ ", type=" + type + ", info=" + info + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", Id()=" + getId() + ", Created()=" + getCreated() + ", Updated()=" + getUpdated() + "]";
	}

}
