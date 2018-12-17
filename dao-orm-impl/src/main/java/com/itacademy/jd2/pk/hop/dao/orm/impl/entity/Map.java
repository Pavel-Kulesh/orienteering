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
    private Set<IRoute> routes = new HashSet<>();

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public BigDecimal getLatitude1() {
        return latitude1;
    }

    @Override
    public void setLatitude1(BigDecimal latitude1) {
        this.latitude1 = latitude1;
    }

    @Override
    public BigDecimal getLatitude2() {
        return latitude2;
    }

    @Override
    public void setLatitude2(BigDecimal latitude2) {
        this.latitude2 = latitude2;
    }

    @Override
    public BigDecimal getLongitude1() {
        return longitude1;
    }

    @Override
    public void setLongitude1(BigDecimal longitude1) {
        this.longitude1 = longitude1;
    }

    @Override
    public BigDecimal getLongitude2() {
        return longitude2;
    }

    @Override
    public void setLongitude2(BigDecimal longitude2) {
        this.longitude2 = longitude2;
    }

    @Override
    public ICustomer getCustomer() {
        return customer;
    }

    @Override
    public void setCustomer(ICustomer customer) {
        this.customer = customer;
    }

    public Set<IRoute> getRoutes() {
        return routes;
    }

    public void setRoutes(Set<IRoute> routes) {
        this.routes = routes;
    }

    @Override
    public byte[] getImage() {
        return image;
    }

    @Override
    public void setImage(byte[] image) {
        this.image = image;
    }

	@Override
	public String toString() {
		return "Map [name=" + name + ", getId()=" + getId() + "]";
	}

   
}
