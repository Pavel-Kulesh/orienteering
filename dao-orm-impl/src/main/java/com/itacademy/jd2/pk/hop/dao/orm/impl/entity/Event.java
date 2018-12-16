package com.itacademy.jd2.pk.hop.dao.orm.impl.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

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

    @Column
    @Version
    private Integer version = 0;

    @JoinTable(name = "customer_2_event", joinColumns = { @JoinColumn(name = "event_id") }, inverseJoinColumns = {
            @JoinColumn(name = "customer_id") })
    @ManyToMany(targetEntity = Customer.class, fetch = FetchType.LAZY)
    private Set<ICustomer> customersList = new HashSet<>();

    @Override
    public Double getLatitude() {
        return latitude;
    }

    @Override
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Override
    public Double getLongitude() {
        return longitude;
    }

    @Override
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String getInfo() {
        return info;
    }

    @Override
    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public ICountry getCountry() {
        return country;
    }

    @Override
    public void setCountry(ICountry country) {
        this.country = country;
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
    public Set<ICustomer> getCustomersList() {
        return customersList;
    }

    @Override
    public void setCustomersList(Set<ICustomer> customersList) {
        this.customersList = customersList;
    }

    @Override
    public Integer getVersion() {
        return version;
    }

    @Override
    public void setVersion(Integer version) {
        this.version = version;

    }

    @Override
    public String toString() {
        return "Event [getId()=" + getId() + "]";
    }

}
