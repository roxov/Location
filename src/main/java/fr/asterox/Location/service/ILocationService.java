package fr.asterox.Location.service;

import java.util.List;
import java.util.UUID;

import fr.asterox.Location.dto.LocationDTO;
import fr.asterox.Location.dto.NearbyAttractionDTO;

/**
 * 
 * Microservice calculating the user location and the attractions around.
 *
 */
public interface ILocationService {
	public String trackUserLocation(String userName);

	public void calculateUserLocation(String userName);

	public List<NearbyAttractionDTO> getFiveNearbyAttractions(LocationDTO visitedLocation, UUID userId);
}
