/**
This file is part of the course CSC5002.

Copyright (C) 2017 Télécom SudParis

This is free software: you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This software platform is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public License
along with the muDEBS platform. If not, see <http://www.gnu.org/licenses/>.

Initial developer(s): Denis Conan
Contributor(s):
 */
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
