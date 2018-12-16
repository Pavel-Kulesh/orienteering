package com.itacademy.jd2.pk.hop.web.dto;

import java.util.List;

public class RouteDataResponse {
	
	private List<PointDTO> points;
	
	private String name;

	public List<PointDTO> getPoints() {
		return points;
	}

	public void setPoints(List<PointDTO> points) {
		this.points = points;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	

}
