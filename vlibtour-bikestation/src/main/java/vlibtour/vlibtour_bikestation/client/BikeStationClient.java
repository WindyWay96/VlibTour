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

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Properties;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import vlibtour.vlibtour_bikestation.client.generated_from_json.Station;

/**
 * The bike station REST client example.
 */
public final class BikeStationClient {
	
	/**
	 * utility class with no instance.
	 */
	private static String restURI;
	
	private BikeStationClient() {
	}
	
	public static void main(final String[] args) throws IOException {
		Properties properties = new Properties();
		FileInputStream input = new FileInputStream("src/main/resources/rest.properties");
		properties.load(input);
		restURI = "http://" + properties.getProperty("rest.serveraddress") + "/MyServer";
		
		Client client = ClientBuilder.newClient();
		URI uri = UriBuilder.fromUri(restURI).build();
		WebTarget service = client.target(uri);
		
		System.out.println("init bike station : \n" + 
				service.path("stations/init").request().accept(MediaType.TEXT_XML).get(String.class));
		
		System.out.println("-------------------------------------");
		
		System.out.println("all stations in plain text : \n" + 
				service.path("stations/alltxt").request().accept(MediaType.TEXT_PLAIN).get(String.class));
		
		System.out.println("-------------------------------------");
		
		System.out.println("all stations in XML		: \n" + 
				service.path("stations/all").request().accept(MediaType.TEXT_XML).get(Station.class));
		
		System.out.println("-------------------------------------");
		
		System.out.println("all stations in json    : \n" + 
				service.path("station/alljson").request().accept(MediaType.APPLICATION_JSON).get(Station.class));
	}
}
