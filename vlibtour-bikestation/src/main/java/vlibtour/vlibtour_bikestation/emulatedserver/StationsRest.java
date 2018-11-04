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

import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import io.restassured.path.json.JsonPath;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;

import org.apache.commons.io.IOUtils;
//import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import vlibtour.vlibtour_bikestation.emulatedserver.generated_from_json.Station;
import vlibtour.vlibtour_visit_emulation.GPSPosition;

/**
 * The bike stations emulated REST server.
 */

@Path("/stations")
public final class StationsRest {
	
	
	private String fileName = "src/main/resources/paris.json";
	private Stations stations;
	
	private void JsonToJV(final String fileName) throws IOException {
		ObjectMapper mapper = new ObjectMapper(); //jackson class for converting from json to java 
		List<Station> stationList = Arrays.asList(mapper.readValue(new File(fileName), Station[].class)); // get the array of stations and convert to a list
		stations = new Stations(stationList);// create a Stations instance
	}
	/**
	 * print all bike stations in Paris
	 * @return list of bike stations in Paris
	 * @throws IOException
	 */
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Station[] getAllStations() throws IOException {
		JsonToJV(fileName);
		return (Station[]) stations.getStations().toArray();
	}
	
	
	/**
	 * 
	 * @param number
	 * @return the station of the given number id
	 * @throws JAXBException
	 * @throws IOException
	 */
	@GET
	@Path("/search/{number}")
	@Produces(MediaType.APPLICATION_JSON)
	public Station getOneStation(@PathParam("number") final long number)  throws JAXBException, IOException {
		JsonToJV(fileName);
		return stations.lookupId(number);
	}
	
	@GET
	@Path("/searchNearest")
	@Produces(MediaType.APPLICATION_JSON)
	public Station getNearestStation(@QueryParam("lat") final double latitude, @QueryParam("lng") double longitude) throws IOException {
		JsonToJV(fileName);
		GPSPosition destination = new GPSPosition(latitude, longitude);
		return stations.findNearestStation(destination);
	}
}
