package com.itacademy.jd2.pk.hop.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.itacademy.jd2.pk.hop.dao.api.entity.INews;

@Entity
public class News extends BaseEntity implements INews {
    @Column
    private String name;
    @Column
    private String info;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
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
    public String toString() {
        return "News [name=" + name + ", getId()=" + getId() + "]";
    }

}