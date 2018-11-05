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
Contributor(s): Nguyen Duyen Phuc <duyen.nguyen@telecom-sudparis.eu/>
				Ngo Hoang Phong <?email/>
 */
package vlibtour.vlibtour_tour_management.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import vlibtour.vlibtour_visit_emulation.GPSPosition;
/**
 * The entity bean defining a point of interest (POI). A {@link Tour} is a
 * sequence of points of interest.
 * 
 * @author Denis Conan
 * @author Nguyen Duyen Phuc
 * @author Ngo Hoang Phong
 */

@Entity
@Table(name="POIS")
@NamedQueries({
    @NamedQuery(
        name = "findPOIById",
        query = "from POIS p where p.poid = :namePOI"
        ),
    @NamedQuery(
            name = "findPOIsByTourId",
            query = "from POIS p where u.tour.id = :namePOI"
            ),
})

public class POI implements Serializable {
	/**
	 * the serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private int poid;
	@Column(name = "POI_Name") private String namePOI;
	@Column(name = "POI_Description") private String descriptionPOI;
	@Column(name = "POI_Address") private String addressPOI;
	@JoinColumn(name = "TourId") private Tour tour;
	private GPSPosition gpsPosition;

	/***************
     * Constructor *
     ***************/
	
	/**
	 * gets id the POI.
	 * 
	 * @return id the POI.
	 */

	public int getPoid() {
		return poid;
	}

	/**
	 * sets the id of POI.
	 * 
	 * @param poid
	 *            the new id of POI.
	 */

	public void setPoid(int poid) {
		this.poid = poid;
	}

	
	/**
	 * gets the name of the POI.
	 * 
	 * @return the name of the POI.
	 */
	
	public String getNamePOI() {
		return namePOI;
	}
	
	/**
	 * sets the name of POI.
	 * 
	 * @param namePOI
	 *            the new name of POI.
	 */
	public void setNamePOI(String namePOI) {
		this.namePOI = namePOI;
	}
	
	/**
	 * gets the description of the POI.
	 * 
	 * @return the description of the POI.
	 */
	public String getDescriptionPOI() {
		return descriptionPOI;
	}

	/**
	 * sets the description of POI.
	 * 
	 * @param namePOI
	 *            the new description of POI.
	 */
	public void setDescriptionPOI(String descriptionPOI) {
		this.descriptionPOI = descriptionPOI;
	}
		
	/**
	 * gets the address of the POI.
	 * 
	 * @return the address of the POI.
	 */

	public String getAddressPOI() {
		return addressPOI;
	}

	/**
	 * sets the address of POI.
	 * 
	 * @param namePOI
	 *            the new address of POI.
	 */
	public void setAddressPOI(String addressPOI) {
		this.addressPOI = addressPOI;
	}
	
	/**
	 * gets the tour.
	 * 
	 * @return tour.
	 */
	public Tour getTour() {
        return tour;
    }
	
	/**
	 * sets the tour.
	 * 
	 * @param tour
	 *            the new tour.
	 */
    public void setTour(Tour tour) {
        this.tour = tour;
    }

	/**
	 * gets the GPS position of the POI.
	 * 
	 * @return the GPS position of the POI.
	 */
	public GPSPosition getGpsPosition() {
		return gpsPosition;
	}

	/**
	 * sets the GPS position of POI.
	 * 
	 * @param namePOI
	 *            the new GPS position of POI.
	 */
	public void setGpsPosition(GPSPosition gpsPosition) {
		this.gpsPosition = gpsPosition;
	}

//	public String getName() {
//	throw new UnsupportedOperationException("Not implemented, yet.");
//}
	
}
