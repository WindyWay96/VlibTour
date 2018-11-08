// CHECKSTYLE:OFF
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
package vlibtour.vlibtour_map_viewer;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.json.Json;

import org.openstreetmap.gui.jmapviewer.MapMarkerDot;

import com.fasterxml.jackson.databind.ObjectMapper;

import vlibtour.vlibtour_visit_emulation.ExampleScenarioTouristsInParis;
import vlibtour.vlibtour_visit_emulation.GPSPosition;
import vlibtour.vlibtour_visit_emulation.GraphOfPositionsForEmulation;
import vlibtour.vlibtour_visit_emulation.Position;

import vlibtour.vlibtour_bikestation.emulatedserver.Stations;
import vlibtour.vlibtour_bikestation.emulatedserver.generated_from_json.Station;
/**
 * This class contains a demonstration of the utility methods for displaying
 * POIs and tourists on a Open Street Map.
 * 
 * @author Chantal Taconet
 * @author Denis Conan
 */
public final class MapDemo {
	
	private static String fileName = "src/main/resources/paris.json";
	private Stations stations1;
	private Stations stations2;
	private Stations stations3;
	private Stations stations4;
	private Stations stations6;
	
	/**
	 * private constructor to avoid instantiation.
	 */
	private MapDemo() {
	}
	
	
	private Stations JsonToJV(final String fileName) throws IOException {
		ObjectMapper mapper = new ObjectMapper(); //jackson class for converting from json to java 
		List<Station> stationList = Arrays.asList(mapper.readValue(new File(fileName), Station[].class)); // get the array of stations and convert to a list
		return new Stations(stationList);// create a Stations instance
		
	}
	
	/**
	 * the main of the example.
	 * 
	 * @param args there is no command line arguments.
	 * @throws Exception pb in using OSM cache...
	 */
	
	public static void main(final String[] args) throws Exception {
		// create a map (JMapViewer) centered between "Musée Grévin" and "Catacombes"
		// with a 14 zoom level
		// The tiles for this map have been previously downloaded to
		// src/main/resources/osm-mapnik
		BasicMap map = MapHelper.createMapWithCenterAndZoomLevel("src/main/resources/osm-mapnik/", 48.851412, 2.343166,
				14);
		Font font = new Font("name", Font.PLAIN, 11);
		// Add markers for the POIs in the tour
		MapHelper.addMarkerDotOnMap(map, 48.871799, 2.342355, Color.BLACK, font, "Musée Grevin");
		MapHelper.addMarkerDotOnMap(map, 48.860959, 2.335757, Color.BLACK, font, "Pyramide du Louvres");
		MapHelper.addMarkerDotOnMap(map, 48.833566, 2.332416, Color.BLACK, font, "Les catacombes");
		
		MapDemo mapDemo = new MapDemo();
		mapDemo.stations1 = mapDemo.JsonToJV(fileName);
		mapDemo.stations2 = mapDemo.JsonToJV(fileName);
		mapDemo.stations3 = mapDemo.JsonToJV(fileName);
		mapDemo.stations4 = mapDemo.JsonToJV(fileName);
		
		mapDemo.stations6 = mapDemo.JsonToJV(fileName);
		
		GPSPosition poi1 = new GPSPosition(48.871799, 2.342355);	
		GPSPosition poi2 = new GPSPosition(48.860959, 2.335757);
		GPSPosition poi3 = new GPSPosition(48.833566, 2.332416);
		
		
		
		// find stations near the users
		GPSPosition p1 = new GPSPosition(48.852424, 2.321045);
	
				
		
		
		mapDemo.stations1.proxyStations(poi1);
		mapDemo.stations2.proxyStations(poi2);
		mapDemo.stations3.proxyStations(poi3);
		mapDemo.stations4.proxyStations(p1);
	
		
		List<Stations> stationsList = new ArrayList<Stations>();
		stationsList.add(mapDemo.stations1);
		stationsList.add(mapDemo.stations2);
		stationsList.add(mapDemo.stations3);
		stationsList.add(mapDemo.stations4);
		
		
		for(int i = 0; i < stationsList.size(); i++) {
			switch(i) {
				case 0 :
					for (Station s : stationsList.get(i).getStations()) {
						MapHelper.addMarkerDotOnMap(map, s.getPosition().getLat(), s.getPosition().getLng(), 
								Color.BLUE, font, "\n" + Double.toString(Math.round(s.getDistanceFrom(poi1))) + "m with " + s.getAvailableBikes() + " bikes available");
					}
					break;
				
				case 1 :
					for (Station s : stationsList.get(i).getStations()) {
						MapHelper.addMarkerDotOnMap(map, s.getPosition().getLat(), s.getPosition().getLng(), 
								Color.BLUE, font, "\n" + Double.toString(Math.round(s.getDistanceFrom(poi2))) + "m with " + s.getAvailableBikes() + " bikes available");
					}
					break;
				case 2 :
					for (Station s : stationsList.get(i).getStations()) {
						MapHelper.addMarkerDotOnMap(map, s.getPosition().getLat(), s.getPosition().getLng(), 
								Color.BLUE, font, "\n" + Double.toString(Math.round(s.getDistanceFrom(poi3))) + "m with " + s.getAvailableBikes() + " bikes available");
					}
					break;
				case 3 :
					for (Station s : stationsList.get(i).getStations()) {
						MapHelper.addMarkerDotOnMap(map, s.getPosition().getLat(), s.getPosition().getLng(), 
								Color.BLUE, font, "\n" + Double.toString(Math.round(s.getDistanceFrom(p1))) + "m with " + s.getAvailableBikes() + " bikes available");
					}
					break;
			}
		}
		// Set the visit (only the last POI on path 47=catacombes)
		List<Position> visit = new ArrayList<>();
		visit.add(new Position(String.valueOf(47), null));
		// Set the graph of positions for emulation
		GraphOfPositionsForEmulation.setAdjacencySets(ExampleScenarioTouristsInParis.initTourWithPOIs());
		// set initial position of the users
		GraphOfPositionsForEmulation.setStartingPosition("Joe",
				new Position(String.valueOf(2), new GPSPosition(48.852424, 2.321045)));
		GraphOfPositionsForEmulation.setStartingPosition("Avrel",
				new Position(String.valueOf(2), new GPSPosition(48.852424, 2.321045)));
		GraphOfPositionsForEmulation.setStartingPosition("William",
				new Position(String.valueOf(2), new GPSPosition(48.852424, 2.321045)));
		
		// set the path to the next POI
		GraphOfPositionsForEmulation.setAPathTo("Joe",
				new Position(String.valueOf(46), new GPSPosition(48.835436, 2.333569)));
		GraphOfPositionsForEmulation.setAPathTo("Avrel",
				new Position(String.valueOf(46), new GPSPosition(48.835436, 2.333569)));
		GraphOfPositionsForEmulation.setAPathTo("William",
				new Position(String.valueOf(46), new GPSPosition(48.835436, 2.333569)));
		
		
		
		// First paint
		MapMarkerDot joeDot = MapHelper.addTouristOnMap(map, Color.RED, font, "Joe");
		MapMarkerDot avrelDot = MapHelper.addTouristOnMap(map, Color.GREEN, font, "Avrel");
		MapMarkerDot williamDot = MapHelper.addTouristOnMap(map, Color.YELLOW, font, "William");
		
		// wait for painting the map, not necessary with the cache
		// Thread.sleep(15000);
		System.out.println("\nDeparture...\n");
		// While they are not all arrived to the destination point (catacombes)
		while (!GraphOfPositionsForEmulation.isAtLastPositionOfCurrentPath("Joe")
				&& !GraphOfPositionsForEmulation.isAtLastPositionOfCurrentPath("Avrel")
				&& !GraphOfPositionsForEmulation.isAtLastPositionOfCurrentPath("William")) {
			// Users one step further
			if (!GraphOfPositionsForEmulation.isAtLastPositionOfCurrentPath("Joe")) {
				GraphOfPositionsForEmulation.stepInCurrentPath("Joe");
			}
			if (!GraphOfPositionsForEmulation.isAtLastPositionOfCurrentPath("Avrel")) {
				GraphOfPositionsForEmulation.stepInCurrentPath("Avrel");
			}
			if (!GraphOfPositionsForEmulation.isAtLastPositionOfCurrentPath("William")) {
				GraphOfPositionsForEmulation.stepInCurrentPath("William");
			}
			// Update users position
			MapHelper.moveTouristOnMap(joeDot);
			MapHelper.moveTouristOnMap(avrelDot);
			MapHelper.moveTouristOnMap(williamDot);
			// Display the map
			map.map().repaint();
			// wait for the next step
			Thread.sleep(1000);
		}
		System.out.println("\nArrival !\n");
		System.exit(0);
	}
}
