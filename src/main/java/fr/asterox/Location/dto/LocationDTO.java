package fr.asterox.Location.dto;

public class LocationDTO {
	public final double latitude;
	public final double longitude;

	public LocationDTO(double latitude, double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

}
