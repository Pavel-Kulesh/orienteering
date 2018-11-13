package com.itacademy.jd2.pk.hop.dao.api.entity;

import java.util.Set;

public interface ICustomer extends IBaseEntity {
	String getName();

	void setName(String name);

	String getSurname();

	void setSurname(String surname);

	String getPhone();

	void setPhone(String phone);

	ICity getCity();

	void setCity(ICity city);

	IUserAccount getUserAccount();

	void setUserAccount(IUserAccount userAccount);

	/*Set<Event> getEventsList();

	public void setEventsList(Set<Event> eventsList);

	void addEventToList(Event event);
*/
	/*void deleteEventFromList(IEvent event);
	
	Set<IEvent> getEventsList();

	public void setEventsList(Set<IEvent> eventsList);

	void addEventToList(IEvent event);*/

	
}
