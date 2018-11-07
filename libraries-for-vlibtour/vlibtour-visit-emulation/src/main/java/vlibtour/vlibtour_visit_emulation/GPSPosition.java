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
Contributor(s): Chantal Taconet
 */
package vlibtour.vlibtour_visit_emulation;
import com.grum.geocalc.Point;
import com.grum.geocalc.DegreeCoordinate;
import com.grum.geocalc.EarthCalc;


/**
 * The class represents a GPS position. It uses the class
 * {@link com.grum.geocalc.Point} by delegation and provides utilitarian
 * methods.
 * 
 * @author Denis Conan
 * @author Chantal Taconet
 */
public class GPSPosition {
	/**
	 * the attribute for the delegation to the position.
	 */
	private Point point;
	
	/**
	 * constructs a GPS position.
	 * 
	 * @param latitude
	 *            the latitude.
	 * @param longitude
	 *            the longitude.
	 */
	public GPSPosition(final double latitude, final double longitude) {
		point = new Point(new DegreeCoordinate(latitude), new DegreeCoordinate(longitude));
	}

	/**
	 * computes the distance from this location to a target location in meters (crow
	 * flies).
	 * 
	 * @param target
	 *            the target location to calculate the distance from.
	 * @return the distance in meter.
	 */

	public double distanceFrom(final GPSPosition target) {
		Point targetPoint = new Point(new DegreeCoordinate(target.getLatitude()),
				new DegreeCoordinate(target.getLongitude()));
		return EarthCalc.getDistance(point, targetPoint);
	}

	/**
	 * gets the longitude.
	 * 
	 * @return the longitude.
	 */
	public double getLongitude() {
		return point.getLongitude();
	}

	/**
	 * gets the latitude.
	 * 
	 * @return the latitude.
	 */
	public double getLatitude() {
		return point.getLatitude();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((point == null) ? 0 : point.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		GPSPosition other = (GPSPosition) obj;
		if (point == null) {
			if (other.point != null) {
				return false;
			}
		} else if (!point.equals(other.point)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "longitude=" + point.getLongitude() + " and latitude=" + point.getLatitude();
	}
}
