package com.itacademy.jd2.pk.hop.web.dto;

public class SpeedDTO {

	private Double distance;

	private Integer diffTime;

	public SpeedDTO(Double distance, Integer diffTime) {
		super();
		this.distance = distance;
		this.diffTime = diffTime;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Integer getDiffTime() {
		return diffTime;
	}

	public void setDiffTime(Integer diffTime) {
		this.diffTime = diffTime;
	}

}
