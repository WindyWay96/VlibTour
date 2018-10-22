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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;



/**
 * The entity bean defining a point of interest (POI). A {@link Tour} is a
 * sequence of points of interest.
 * 
 * @author Denis Conan
 */
public class POI implements Serializable {
	/**
	 * the serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * gets the name of the POI.
	 * 
	 * @return the name of the POI.
	 */
	public String getName() {
		throw new UnsupportedOperationException("Not implemented, yet.");
	}
	
	@Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;
    
    POI() {
        
    }
    
    public POI(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    /**
     * stop_lat Required The stop_lat field contains the latitude of a stop or station. The field
     * value must be a valid WGS 84 latitude.
     *
     * @return latitude coordinate for this location.
     */
    public double getLat() {
        return this.latitude;
    }

    /**
     * stop_lon Required The stop_lon field contains the longitude of a stop or station. The field
     * value must be a valid WGS 84 longitude value from -180 to 180.
     *
     * @return longitude coordinate for this location.
     */
    public double getLng() {
        return this.longitude;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
    
    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }
    
    @Override
    public String toString() {
        return new ReflectionToStringBuilder(this).build();
    }


}
