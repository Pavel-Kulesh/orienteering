package com.itacademy.jd2.pk.hop.web.dto;

import java.util.Date;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.itacademy.jd2.pk.hop.dao.api.entity.TypeEvent;

public class EventDTO {

	private Integer version;

	private Integer id;
	@NotEmpty
	private String name;
	@NotNull
	private Integer customerId;

	private String countryName;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	@NotNull
	private Integer countryId;
	@NotNull
	private TypeEvent type;
	@NotNull
	private String info;
	@DecimalMin(value = "-90")
	@DecimalMax(value = "90")
	private Double latitude;
	@DecimalMin(value = "-180")
	@DecimalMax(value = "180")
	private Double longitude;

	private Date created;

	private Date updated;
	
	private boolean statusVisible;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public boolean getStatusVisible() {
		return statusVisible;
	}

	public void setStatusVisible(boolean statusVisible) {
		this.statusVisible = statusVisible;
	}

		

}
