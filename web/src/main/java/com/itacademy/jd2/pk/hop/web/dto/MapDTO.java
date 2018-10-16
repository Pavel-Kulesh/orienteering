package com.itacademy.jd2.pk.hop.web.dto;

import java.math.BigDecimal;
import java.util.Date;

public class MapDTO {

	private Integer id;
	private String name;
	private Integer userId;
	private String path;
	private String file;
	private BigDecimal latitude1;
	private BigDecimal longitude1;
	private BigDecimal latitude2;
	private BigDecimal longitude2;
	private Date created;
	private Date updated;

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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	

	public BigDecimal getLongitude2() {
		return longitude2;
	}

	public void setLongitude2(BigDecimal longitude2) {
		this.longitude2 = longitude2;
	}

	public BigDecimal getLatitude1() {
		return latitude1;
	}

	public void setLatitude1(BigDecimal latitude1) {
		this.latitude1 = latitude1;
	}

	public BigDecimal getLongitude1() {
		return longitude1;
	}

	public void setLongitude1(BigDecimal longitude1) {
		this.longitude1 = longitude1;
	}

	public BigDecimal getLatitude2() {
		return latitude2;
	}

	public void setLatitude2(BigDecimal latitude2) {
		this.latitude2 = latitude2;
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

}
