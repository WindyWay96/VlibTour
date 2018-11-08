package vlibtour.vlibtour_tour_management.api;

import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import vlibtour.vlibtour_tour_management.entity.POI;
import vlibtour.vlibtour_tour_management.entity.Tour;

/**
 * This interface defines the operation for managing POIs and Tours.
 * 
 * @author Denis Conan
 */
@Remote
public interface VlibTourTourManagement {
	
	/**
	 * List the set of tours
	 * 
	 * @return the list of tour ID, tour name
	 * 
	 */
	List<Tour> findAllTours();
	Tour findTourById(int Id);
	String createTour(final int tourID, final String tourName, final String tourDescription, final String tourStatus) ;
	String removeTour(int Id );
	String createPOI (final int poid, final String poiName, final String poiDescription,
			final String gpsLocation, final String duration);
	String addPoiToTour(final int tourID, final int poid);
	List <POI> listPOIs();
	POI findPOI(int Id);
	
}