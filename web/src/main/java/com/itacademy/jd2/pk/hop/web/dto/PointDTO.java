package com.itacademy.jd2.pk.hop.web.dto;

public class PointDTO {

	private Double longitude;

	private Double latitude;

	public PointDTO(Double latitude, Double longitude) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

}
