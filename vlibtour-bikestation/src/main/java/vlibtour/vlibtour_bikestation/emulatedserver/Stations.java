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

Initial developer(s): Chantal Taconet
Contributor(s): Denis Conan
 */
package vlibtour.vlibtour_bikestation.emulatedserver;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import vlibtour.vlibtour_bikestation.emulatedserver.generated_from_json.Station;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 * The stations REST server.
 */
public class Stations {
	
	@XmlElementWrapper(name = "stations")
	@XmlElement(name = "station")
	private List<Station> stations = new ArrayList<Station>();

	/**
	 * default constructor.
	 */
	public Stations() {
	}

	
	public Stations(final List<Station> stations) {
		this.stations = stations;
	}

	
	public void add(final Station station) {
		stations.add(station);
	}
	
	public Station lookupId(final long id) {
		Station station;
		for (Iterator<Station> it = stations.iterator(); it.hasNext();) {
			station = it.next();
			if (station.getNumber() == id) {
				return station;
			}
		}
		return null;
	}
	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		for (Iterator<Station> it = stations.iterator(); it.hasNext();) {
			output = output.append(it.next() + "\n");
		}
		return output.toString();
	}
}
