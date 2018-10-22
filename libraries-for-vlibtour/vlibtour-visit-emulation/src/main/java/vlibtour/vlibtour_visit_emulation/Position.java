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
package vlibtour.vlibtour_visit_emulation;

/**
 * The class represents a position.
 * 
 * @author Denis Conan
 */
public class Position {
	/**
	 * the name of the position.
	 */
	private String name;
	/**
	 * the GPS position.
	 */
	private GPSPosition gpsLocation;
	/**
	 * the description.
	 */
	private String description;
	/**
	 * the corresponding POI. The type is {@link Object} because the solution will
	 * provide its right type.
	 */
	private Object poi;

	/**
	 * constructs a position that may have a reference to a POI.
	 * 
	 * @param name
	 *            the name of the position.
	 * @param gpsLocation
	 *            the GPS location.
	 *            @param description the description, for instance the address.
	 * @param poi
	 *            the corresponding POI.
	 */
	public Position(final String name, final GPSPosition gpsLocation, final String description, final Object poi) {
		if (name == null || name.equals("")) {
			throw new IllegalArgumentException("name of position is null or empty");
		}
		this.name = name;
		this.gpsLocation = gpsLocation;
		this.description = description;
		this.poi = poi;
	}

	/**
	 * constructs a position that has no POI.
	 * 
	 * @param name
	 *            the name of the position.
	 * @param gpsPosition
	 *            the GPS location.
	 * @param description
	 *            the description, for instance the address.
	 */
	public Position(final String name, final GPSPosition gpsPosition, final String description) {
		this(name, gpsPosition, description, null);
	}

	/**
	 * constructs a position that has no description and no POI.
	 * 
	 * @param name
	 *            the name of the position.
	 * @param gpsPosition
	 *            the GPS location.
	 */
	public Position(final String name, final GPSPosition gpsPosition) {
		this(name, gpsPosition, null, null);
	}

	/**
	 * gets the name of position.
	 * 
	 * @return the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * gets the GPS position.
	 * 
	 * @return the GPS position.
	 */
	public GPSPosition getGpsLocation() {
		return gpsLocation;
	}

	/**
	 * gets the description.
	 * 
	 * @return the description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * gets the corresponding POI, if exists.
	 * 
	 * @return the POI or null reference.
	 */
	public Object getPoi() {
		return poi;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Position other = (Position) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Position [name=" + name + ", location is " + gpsLocation + ", description=" + description + ", poi="
				+ poi + "]";
	}
}
