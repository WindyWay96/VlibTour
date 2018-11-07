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
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.Properties;
import java.util.TreeMap;
import java.util.Set;
import java.util.SortedMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.mail.imap.protocol.Item;

import vlibtour.vlibtour_bikestation.emulatedserver.generated_from_json.Station;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import vlibtour.vlibtour_visit_emulation.GPSPosition;

/**
 * The stations REST server.
 */
public class Stations {
	
	@XmlElementWrapper(name = "stations")
	@XmlElement(name = "station")
	
	private List<Station> stations;

	/**
	 * default constructor.
	 */
	public Stations() {
	}
	
	public Stations(final List<Station> stations) {
		this.stations = stations;
	}
	
	public List<Station> getStations() {
		return this.stations;
	}

	public void add(final Station station) {
		stations.add(station);
	}
	
	public Station lookupId(final long id) {
		Station station;
		for (Iterator<Station> it = stations.iterator(); it.hasNext(); ) {
			station = it.next();
			if (station.sameNumber(id)) {
				return station;
			}
		}
		return null;
	}
	
	public Station findNearestStation(final GPSPosition destination) {
		ArrayList<Double> distances = new ArrayList<Double>(stations.size());
		for (Station station : stations) {
			distances.add(station.getDistanceFrom(destination));
		}
		return stations.get(distances.indexOf(Collections.min(distances)));
	}
	
	public void proxyStations(final GPSPosition destination) {
		List<Double> distances = new ArrayList<Double>();
		List<Long> nbBikeList = new ArrayList<Long>();
		List<Station> proxies = new ArrayList<Station>();
		List<Long> idList = new ArrayList<Long>();
		
		for (Station station : stations) {
			distances.add(station.getDistanceFrom(destination));
			nbBikeList.add(station.getAvailableBikes());
		}
		
		Map<Double, Long> distanceOrder = new HashMap<Double, Long>();
		for (int i = 0; i < stations.size(); i++)
		{
		    Double dist = distances.get(i);
		    Long id= stations.get(i).getNumber();
		    distanceOrder.put(dist, id);
		}
		
		SortedMap<Double, Long> newMap = new TreeMap<Double, Long>(distanceOrder);
		for(Long id : newMap.values()) {
			idList.add(id);
		}
		
		for(Long id : idList) {
			for(Station s : stations) {
				if (s.sameNumber(id)) { 
					proxies.add(s) ;
				}
			}
		}

		List<Station> result = new ArrayList<Station>(5);
		
		for(int i = 0; i < 5; i++) {
			result.add(proxies.get(i));
		}
		stations = result;
	}
	
	public class ItemDistanceComparator implements Comparator<Station>
	{
	    private Map<Double, Long> sortOrder;
	    private GPSPosition position;
	    
	    public ItemDistanceComparator(Map<Double, Long> sortOrder, GPSPosition position)
	    {
	        this.sortOrder = sortOrder;
	        this.position = position;
	    }

	    @Override
	    public int compare(Station i1, Station i2)
	    {
	        Long weekdayPos1 = sortOrder.get(i1.getDistanceFrom(position));
	        Long weekdayPos2 = sortOrder.get(i2.getDistanceFrom(position));
	        return weekdayPos1.compareTo(weekdayPos2);
	    }
	}
	
	public void sortArray() {
		Collections.sort(stations, new Comparator<Station>()
		{
			public int compare(Station s1, Station s2) {
				return Integer.valueOf((int)s1.getAvailableBikes()).compareTo((int)s2.getAvailableBikes());
			}
		});
	}
	
	public Station getStationWithMinNbBike() {
		long minValue = stations.get(0).getAvailableBikes();
		for (int i = 1; i < stations.size(); i++) {
			if (stations.get(i).getAvailableBikes() < minValue) {
				minValue = stations.get(i).getAvailableBikes();
			}
		}
		for (Station station : stations) {
			if (station.getAvailableBikes() == minValue) 
				return station;
		}
		return null;
	}
	
	public Station getStationFromNbBike(long nbBike) {
		for(Station station : stations) {
			if (station.getAvailableBikes() == nbBike) {
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
