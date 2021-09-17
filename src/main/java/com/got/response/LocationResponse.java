package com.got.response;

import java.util.List;

import com.got.projection.LocationProjection;

public class LocationResponse {

	public Integer statusCode;
	public String message;
	public List<LocationProjection> locations;

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<LocationProjection> getLocations() {
		return locations;
	}

	public void setLocations(List<LocationProjection> locations) {
		this.locations = locations;
	}

}
