package fr.asterox.Location.service;

import java.util.List;
import java.util.UUID;

import fr.asterox.Location.dto.LocationDTO;
import fr.asterox.Location.dto.NearbyAttractionDTO;
import gpsUtil.location.VisitedLocation;

/**
 * 
 * Microservice calculating the user location and the attractions around.
 *
 */
public interface ILocationService {
	public VisitedLocation trackUserLocation(String userName);

	public List<NearbyAttractionDTO> getFiveNearbyAttractions(LocationDTO visitedLocation, UUID userId);
}
