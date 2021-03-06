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
package vlibtour.vlibtour_bikestation.client;

import java.awt.List;

//import static org.junit.Assert.assertThat;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import vlibtour.vlibtour_bikestation.client.generated_from_json.Station;
import vlibtour.vlibtour_bikestation.emulatedserver.Stations;


/**
 * The bike station REST client example.
 */
public final class BikeStationClient {
	
	/**
	 * the URI.
	 */
	private static String restURI;

	/**
	 * the default constructor is private to avoid instantiation.
	 */
	private BikeStationClient() {
	}

	/**
	 * the main method.
	 * 
	 * @param args
	 *            there is no command line arguments.
	 * @throws IOException
	 *             problem with HTTP connection.
	 */
	public static void main(final String[] args) throws IOException {
		callEmulated();
		//uncomment to test with the jcdecaux server
		//clientSide();
	}
	
	public static void clientSide() throws IOException {
		Properties properties = new Properties();
		FileInputStream input = new FileInputStream("src/main/resources/rest.properties");
		properties.load(input);
		
		String contract = properties.getProperty("jcdecaux.contract");
		String apiKey = properties.getProperty("jcdecaux.apiKey");
		
	/************************************
	* Test the client with dynamic data
	* in real server (jcdecaux API )
	* input parameter: station id = 2010
	*************************************/	
		
		// https://api.jcdecaux.com/vls/v1/stations/2010?contract=lyon&apiKey=53e15d3779d1c15af0f8a4137a7bbccc2e04f0e0
		
		restURI = properties.getProperty("jcdecaux.rooturl") + "/stations/2010?contract=" + contract + "&apiKey=" + apiKey;
		Client client = ClientBuilder.newClient();
		URI uri = UriBuilder.fromUri(restURI).build();
		WebTarget service = client.target(uri);
		
		// Get streaming data from API
		String response = service.request().accept(MediaType.APPLICATION_JSON).get(String.class);
		
		// Map json data to java object 
		ObjectMapper objectMapper = new ObjectMapper();
		Station station = objectMapper.readValue(response, Station.class);
		
		System.out.println("###################################################"); 
        System.out.println("#     QUERY DYNAMIC DATASET ON JCDECAUX SERVER    #");
        System.out.println("###################################################"); 
		// Print name of station
		System.out.println("Name of station" + station.getName());
		
		// Print address of station
		System.out.println("Address of station is" + " " + station.getAddress());
		
		// How many bikes available?
		System.out.println("There are" + " " + station.getAvailableBikes() + " " + "bikes available in" + " " + station.getAddress());
		// How many bike-stands available?
		System.out.println("There are total" + " " + station.getBikeStands() + " " + "bike-stands available in" + " " + station.getAddress());

	}
	
	/***************************************
	 * Test the client with emulated server
	 **************************************/	
		
	private static void callEmulated() throws IOException {
		Properties properties = new Properties();
		FileInputStream input = new FileInputStream("src/main/resources/rest.properties");
		properties.load(input);		
		restURI = properties.getProperty("emulated.server");
		Client client = ClientBuilder.newClient();
		URI uri = UriBuilder.fromUri(restURI).build();
		WebTarget service = client.target(uri);
		
		//Print all 
		System.out.println("all stations : \n" + service.path("stations/all").request().accept(MediaType.APPLICATION_JSON).get(Station[].class));
		

		//Print a station with a given 
		System.out.println("Input station Id to find that particular station: " 
				+ service.path("stations/search/28002").request().accept(MediaType.APPLICATION_JSON).get(Station.class));
		
		System.out.println("the nearest station to Musee Grevin: \n "
				+ service.path("stations/searchNearest").queryParam("lat", "48.8718").queryParam("lng", "2.3422").request().accept(MediaType.APPLICATION_JSON).get(Station.class));
		System.out.println("the nearest station to Musee du Louvre: \n "
				+ service.path("stations/searchNearest").queryParam("lat", "48.8606").queryParam("lng", "2.3376").request().accept(MediaType.APPLICATION_JSON).get(Station.class));
		
		System.out.println("the bike station nearest to Musee Grevin has: \n"
				+ service.path("stations/getBikes").queryParam("lat", "48.8718").queryParam("lng", "2.3422").request().accept(MediaType.TEXT_PLAIN).get(String.class));
		
		System.out.println("the station with the least number of available bikes: \n"
				+ service.path("stations/minValue").request().accept(MediaType.APPLICATION_JSON).get(Station.class));
		
		System.out.println("nearest stations to Musee du Louvre: \n"
				+ service.path("stations/proxyStations").queryParam("lat", "48.8606").queryParam("lng", "2.3376").request().accept(MediaType.APPLICATION_JSON).get(Station[].class));
	}
}