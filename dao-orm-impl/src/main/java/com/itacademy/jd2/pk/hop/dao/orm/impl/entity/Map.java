package com.itacademy.jd2.pk.hop.dao.orm.impl.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICustomer;
import com.itacademy.jd2.pk.hop.dao.api.entity.IMap;
import com.itacademy.jd2.pk.hop.dao.api.entity.IRoute;

@Entity
public class Map extends BaseEntity implements IMap {
	@Column
	private String name;
	@Column
	private String path;
	@Column
	private String file;
	@Column
	private byte[] image;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Customer.class)
	private ICustomer customer;

	@Column
	private BigDecimal latitude1;
	@Column
	private BigDecimal latitude2;
	@Column
	private BigDecimal longitude1;
	@Column
	private BigDecimal longitude2;

	@JoinTable(name = "map_2_route", joinColumns = { @JoinColumn(name = "map_id") }, inverseJoinColumns = {
			@JoinColumn(name = "route_id") })
	@ManyToMany(targetEntity = Route.class, fetch = FetchType.LAZY)
	// @OrderBy("title ASC")
	private Set<IRoute> routesList = new HashSet<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public BigDecimal getLatitude1() {
		return latitude1;
	}

	public void setLatitude1(BigDecimal latitude1) {
		this.latitude1 = latitude1;
	}

	public BigDecimal getLatitude2() {
		return latitude2;
	}

	public void setLatitude2(BigDecimal latitude2) {
		this.latitude2 = latitude2;
	}

	public BigDecimal getLongitude1() {
		return longitude1;
	}

	public void setLongitude1(BigDecimal longitude1) {
		this.longitude1 = longitude1;
	}

	public BigDecimal getLongitude2() {
		return longitude2;
	}

	public void setLongitude2(BigDecimal longitude2) {
		this.longitude2 = longitude2;
	}

	public ICustomer getCustomer() {
		return customer;
	}

	public void setCustomer(ICustomer customer) {
		this.customer = customer;
	}

	public Set<IRoute> getRoutesList() {
		return routesList;
	}

	public void setRoutesList(Set<IRoute> routesList) {
		this.routesList = routesList;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Map [name=" + name + ", path=" + path + ", file=" + file + ", customer=" + customer + ", latitude1="
				+ latitude1 + ", latitude2=" + latitude2 + ", longitude1=" + longitude1 + ", longitude2=" + longitude2
				+ ", getId()=" + getId() + ", getCreated()=" + getCreated() + ", getUpdated()=" + getUpdated() + "]";
	}

}
