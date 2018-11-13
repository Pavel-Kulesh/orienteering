package com.itacademy.jd2.pk.hop.dao.orm.impl.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICity;
import com.itacademy.jd2.pk.hop.dao.api.entity.ICustomer;
import com.itacademy.jd2.pk.hop.dao.api.entity.IEvent;
import com.itacademy.jd2.pk.hop.dao.api.entity.IUserAccount;

@Entity
public class Customer implements ICustomer {

	@Id
	private Integer id;

	@Column(updatable = false)
	private Date created;

	@Column
	private Date updated;

	@Column
	private String name;

	@Column
	private String surname;

	@Column
	private String phone;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = City.class)
	private ICity city;

	@OneToOne(fetch = FetchType.LAZY, optional = false, targetEntity = UserAccount.class)
	@PrimaryKeyJoinColumn
	private IUserAccount userAccount;

	@ManyToMany(mappedBy = "customersList")
	private Set<Event> eventsList = new HashSet<>();

	public IUserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(IUserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public ICity getCity() {
		return city;
	}

	public void setCity(ICity city) {
		this.city = city;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	/*
	 * public Set<Event> getEventsList() { return eventsList; }
	 * 
	 * public void setEventsList(Set<Event> eventsList) { this.eventsList =
	 * eventsList; }
	 */

	public Set<Event> getEventsList() {
		return eventsList;
	}

	public void setEventsList(Set<Event> eventsList) {
		this.eventsList = eventsList;
	}

	public void addEventToList(Event event) {
		eventsList.add(event);
	}

	public void deleteEventFromList(IEvent event) {
		eventsList.remove(event);

	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", created=" + created + ", updated=" + updated + ", name=" + name + ", surname="
				+ surname + ", phone=" + phone + ", city=" + city + "]";
	}

}
