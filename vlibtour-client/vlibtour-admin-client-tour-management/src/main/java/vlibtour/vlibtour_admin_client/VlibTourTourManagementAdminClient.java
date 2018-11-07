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
package vlibtour.vlibtour_admin_client;

import java.util.List;
import java.util.Set;

import javax.naming.InitialContext;
import javax.naming.NamingException;


import vlibtour.vlibtour_tour_management.api.VlibTourTourManagement;
import vlibtour.vlibtour_tour_management.entity.Tour;

/**
 * This class defines the administration client of the case study vlibtour.
 * <ul>
 * <li>USAGE:
 * <ul>
 * <li>vlibtour.vlibtour_admin_client.VlibTourAdminClient populate toursAndPOIs
 * </li>
 * <li>vlibtour.vlibtour_admin_client.VlibTourAdminClient empty toursAndPOIs
 * </li>
 * </ul>
 * </li>
 * </ul>
 * 
 * @author Denis Conan
 */
public class VlibTourTourManagementAdminClient {
	
	public static void main(final String[] args) {

	VlibTourTourManagement sb;
	try {
		InitialContext ic = new InitialContext();
		sb = (VlibTourTourManagement) ic.lookup("vlibtour.vlibtour_tour_management.api.VlibTourTourManagement");
		String dash = "-------------------";
		System.out.println("####################"); 
        System.out.println("#   ADMIN QUERIES  #");
        System.out.println("####################");
		// Create tour
		System.out.println("Create tour 1 " + sb.createTour(10, "TourName_1", "TourDescription_1", "Available"));
		System.out.println("Create tour 2 " + sb.createTour(11, "TourName_2", "TourDescription_2", "Full"));
		System.out.println("Create tour 3 " + sb.createTour(12, "TourName_3", "TourDescription_3", "Available"));
		System.out.println("Create tour 4 " + sb.createTour(13, "TourName_4", "TourDescription_4", "Available"));
		System.out.println(dash);
		
		// List tour
		System.out.println("List of the tours " + sb.findAllTours());
		System.out.println(dash);
		
		// Find tour with tour ID = 10
		System.out.println("Find tour: " + sb.findTourById(12));
		System.out.println(dash);
		
		// Remove tour with tour ID = 12
		System.out.println(sb.removeTour(13));
		System.out.println(dash);
		
		// Create a POI
		System.out.println("Create POI 1 " + sb.createPOI(20, "Musée Grévin", "Musée de cire sur l’histoire de France", "48,871799 - 2,342355","60 minutes"));
		System.out.println("Create POI 2 " + sb.createPOI(21, "Pyramide du Louvres", "La pyramide du Louvre est une pyramide constitu´ee de verre et de\n" + 
				"m´etal, situ´ee au milieu de la cour Napol´eon du mus´ee du Louvre `a Paris", "48,860959 - 2,335757", "20 minutes"));
		System.out.println("Create POI 3 " + sb.createPOI(22, "Les catacombes de Paris", "Labyrinthe ´eclair´e dans une ancienne mine de calcaire avec des millions\n" + 
				"de squelettes macabres entass´es", "48,833566 - 2,332416", "60 minutes"));
		
		System.out.println(dash);

		
		// Add a POI with poid = 20 to a tour with tourID = 10
		System.out.println("POI 1 has been added to tour 1 " + sb.addPoiToTour(10, 20));
		System.out.println(dash);
		
		System.out.println("####################"); 
        System.out.println("# TOURIST QUERIES  #");
        System.out.println("####################");

		// List tour
		System.out.println("List of the tours " + sb.findAllTours());
		System.out.println(dash);

}  catch(Exception e) {
	e.printStackTrace();
}

}
}
