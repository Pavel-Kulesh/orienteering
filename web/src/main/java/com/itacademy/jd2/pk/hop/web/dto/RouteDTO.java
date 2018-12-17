package com.itacademy.jd2.pk.hop.web.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.itacademy.jd2.pk.hop.dao.api.entity.TypeTrack;

public class RouteDTO {
    private Integer id;

    @NotEmpty
    private String name;

    private TypeTrack track;

    private Date created;
    private boolean canEdit;

    @NotNull
    private Integer customerId;

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

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public TypeTrack getTrack() {
        return track;
    }

    public void setTrack(TypeTrack track) {
        this.track = track;
    }

    public boolean isCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }

}
