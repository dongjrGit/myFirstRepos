package com.gps.VO;

public class Latlons {
	private String Latitude;
	private String longitude;
	public Latlons() {
		super();
	}
	public Latlons(String latitude, String longitude) {
		super();
		Latitude = latitude;
		this.longitude = longitude;
	}
	public String getLatitude() {
		return Latitude;
	}
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	@Override
	public String toString() {
		return "Latlons [Latitude=" + Latitude + ", longitude=" + longitude
				+ "]";
	}
	
}
