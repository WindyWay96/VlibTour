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

import vlibtour.vlibtour_bikestation.client.generated_from_json.Station;

//import static org.junit.Assert.assertThat;

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
		Properties properties = new Properties();
		FileInputStream input = new FileInputStream("src/main/resources/rest.properties");
		properties.load(input);
		
		String contract = properties.getProperty("jcdecaux.contract");
		String apiKey = properties.getProperty("jcdecaux.apiKey");
								
		restURI = properties.getProperty("jcdecaux.rooturl") + "/stations/2010?contract=" + contract + "&apiKey=" + apiKey;
		//https://api.jcdecaux.com/vls/v1/stations/2010?contract=lyon&apiKey=53e15d3779d1c15af0f8a4137a7bbccc2e04f0e0
		Client client = ClientBuilder.newClient();
		URI uri = UriBuilder.fromUri(restURI).build();
		WebTarget service = client.target(uri);
		System.out.println(uri + "test");
		System.out.println("the station in json      : \n"
				+ service.request().accept(MediaType.APPLICATION_JSON).get(String.class));

		
	}
}