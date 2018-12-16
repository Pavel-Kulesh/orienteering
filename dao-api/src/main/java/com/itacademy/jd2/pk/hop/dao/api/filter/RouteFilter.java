package com.itacademy.jd2.pk.hop.dao.api.filter;

public class RouteFilter extends AbstractFilter {
	private String userRole;

	private Integer customerId;

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

}
