package com.itacademy.jd2.pk.hop.dao.jdbc.impl.entity;

import java.math.BigDecimal;
import java.util.Set;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICustomer;
import com.itacademy.jd2.pk.hop.dao.api.entity.IMap;
import com.itacademy.jd2.pk.hop.dao.api.entity.IRoute;

public class Map extends BaseEntity implements IMap {
    private String name;

    private ICustomer customer;
    private BigDecimal latitude1;
    private BigDecimal latitude2;
    private BigDecimal longitude1;
    private BigDecimal longitude2;

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

    @Override
    public String toString() {
        return "Map [name=" + name + ",   customer=" + customer + ", latitude1=" + latitude1 + ", latitude2="
                + latitude2 + ", longitude1=" + longitude1 + ", longitude2=" + longitude2 + ", getId()=" + getId()
                + ", getCreated()=" + getCreated() + ", getUpdated()=" + getUpdated() + "]";
    }

    @Override
    public byte[] getImage() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setImage(byte[] image) {
        // TODO Auto-generated method stub

    }

    @Override
    public Set<IRoute> getRoutes() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setRoutes(Set<IRoute> routes) {
        // TODO Auto-generated method stub

    }

}
