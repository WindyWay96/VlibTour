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
package vlibtour.vlibtour_tour_management.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The entity bean defining a tour in the VLibTour case study. A tour is a
 * sequence of points of interest ({@link POI}).
 * 
 * @author Denis Conan
 * @author Nguyen Duyen Phuc
 * @author Ngo Hoang Phong
 */

@Entity
@Table(name="TOURS")
@NamedQueries({
    @NamedQuery(
        name = "findTourById",
        query = "from TOURS t where t.tourID = :tourName"
        ),
    @NamedQuery(
            name = "checkTourStatus",
            query = "from TOURS t where t.tourID = :tourStatus"
            ),
})

public class Tour implements Serializable {
	/**
	 * the serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private int tourID;
	
	@JoinColumn(name = "dateId") private int dateID;
	
	@Column (name = "TourName") private String tourName;
	@Column (name = "TourDescription")private String tourDescription;
	@Column (name = "TourStatus") private String tourStatus;
	
	
	/***************
     * Constructor *
     ***************/
	

	/**
	 * gets the name of the tour.
	 * 
	 * @return the name of the tour.
	 */
	public String getName() {
		throw new UnsupportedOperationException("Not implemented, yet.");
	}


	public int getTourID() {
		return tourID;
	}


	public void setTourID(int tourID) {
		this.tourID = tourID;
	}


	public String getTourName() {
		return tourName;
	}


	public void setTourName(String tourName) {
		this.tourName = tourName;
	}


	public String getTourDescription() {
		return tourDescription;
	}


	public void setTourDescription(String tourDescription) {
		this.tourDescription = tourDescription;
	}


	public String getTourStatus() {
		return tourStatus;
	}


	public void setTourStatus(String tourStatus) {
		this.tourStatus = tourStatus;
	}


	public int getDateID() {
		return dateID;
	}


	public void setDateID(int dateID) {
		this.dateID = dateID;
	}
}