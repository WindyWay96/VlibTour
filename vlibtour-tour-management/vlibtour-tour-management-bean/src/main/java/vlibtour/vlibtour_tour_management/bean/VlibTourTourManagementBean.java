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
package vlibtour.vlibtour_tour_management.bean;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;



import java.util.List;

import vlibtour.vlibtour_tour_management.api.VlibTourTourManagement;
import vlibtour.vlibtour_tour_management.entity.POI;
import vlibtour.vlibtour_tour_management.entity.Tour;
import vlibtour.vlibtour_tour_management.entity.User;

/**
 * This class defines the EJB Bean of the VLibTour tour management.
 * 
 * @author Denis Conan
 */
@Stateless
public class VlibTourTourManagementBean implements VlibTourTourManagement {

	/**
	 * the reference to the entity manager, which persistence context is "pu1".
	 */
	@PersistenceContext(unitName = "pu1")
	private EntityManager entityManager;

	// Create a tour

	@SuppressWarnings("unchecked")
	@Override
	public String createTour(final int tourID, final String tourName, final String tourDescription,
			final String tourStatus) {

		Query q = entityManager.createQuery("select t from Tour t where t.tourID = :tourID");
		q.setParameter("tourID", tourID);
		List<Tour> listTours = new ArrayList<>();
		listTours = q.getResultList();
		if (listTours != null && listTours.size() != 0) {
			System.out.println("Tour already exists");
		}

		Tour tour = new Tour();
		tour.setTourID(tourID);
		tour.setTourName(tourName);
		tour.setTourDescription(tourDescription);
		tour.setTourStatus(tourStatus);

		entityManager.persist(tour);
		return "Successfull!";

	}

	// List all tours
	@Override
	@SuppressWarnings("unchecked")
	public List<Tour> findAllTours() {
		List<Tour> listTours;
		listTours = entityManager.createQuery("SELECT t FROM Tour t").getResultList();
		if (listTours == null) {
			System.out.println("No tour found . ");
		} else {
			for (Tour tour : listTours) {
				System.out.println("Tour name: " + tour.getTourName() + ", Tour Status:" + tour.getTourStatus());
			}
		}

		return listTours;
	}

	// Find the tour by tourID
	@Override
	public Tour findTourById(int Id) {

		Tour tour = entityManager.find(Tour.class, Id);

		if (tour != null) {
			System.out.println("Tour found  for id = " + Id);
		} else {
			System.out.println("No tour found for id = " + Id);
		}
		return tour;
	}

	// Delete tour by tourID
	@Override
	public String removeTour(int Id) {
		Tour tour = entityManager.find(Tour.class, Id);
		entityManager.remove(tour);
		return "Tour with ID =" + " " + Id + " " + "has been removed";
	}

	// Create a POI
	@SuppressWarnings("unchecked")
	@Override
	public String createPOI(final int poid, final String poiName, final String poiDescription, final String gpsLocation,
			final String duration) {

		Query q = entityManager.createQuery("select p from POI p where p.poid = :poid");
		q.setParameter("poid", poid);

		List<POI> listpois;

		listpois = q.getResultList();
		if (listpois != null && listpois.size() != 0) {
			System.out.println("poi already exists");
		}

		POI poi = new POI();
		poi.setPoid(poid);
		poi.setNamePOI(poiName);
		poi.setDescriptionPOI(poiDescription);
		poi.setGpsPosition(gpsLocation);
		poi.setDuration(duration);

		entityManager.persist(poi);
		return "Successful!";

	}

	@Override
	// Add a POI to a tour
	public String addPoiToTour(final int tourID, final int poid) {
		Query q1 = entityManager.createQuery("select p from POI p where p.poid = :poid");
		q1.setParameter("poid", poid);
		@SuppressWarnings("rawtypes")
		List results = q1.getResultList();
		if (results == null || results.size() == 0) {
			System.out.println("POI is not yet created.");
		}
		POI poi = (POI) q1.getSingleResult();

		Query q2 = entityManager.createQuery("select t from Tour t where t.tourID = :tourID");
		q2.setParameter("tourID", tourID);
		results = q2.getResultList();
		if (results == null || results.size() == 0) {
			throw new RuntimeException("Tour is not yet created.");
		}
		Tour tour = (Tour) q2.getSingleResult();
		tour.getPOIS().add(poi);
		return "Successful";
	}
	

	
	// List POIs
	@Override
	public List<POI> listPOIs() {
		List<POI> listPOIs;
		listPOIs = entityManager.createQuery("SELECT p FROM POI p").getResultList();
		if (listPOIs == null) {
			System.out.println("No POI found . ");
		} else {
			for (POI poi : listPOIs) {
				System.out.println("POI name: " + poi.getNamePOI() + ", POI Description:" + poi.getDescriptionPOI());
			}
		}

		return listPOIs;
	}
	
	// Get info of POI
		@Override
		public POI findPOI(int Id) {

			POI poi = entityManager.find(POI.class, Id);

			if (poi != null) {
				System.out.println("POI found  for id = " + Id);
			} else {
				System.out.println("No POI found for id = " + Id);
			}
			return poi;
		}
	
}
