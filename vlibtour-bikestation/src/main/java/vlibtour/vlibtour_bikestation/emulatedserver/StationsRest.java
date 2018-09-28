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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import vlibtour.vlibtour_bikestation.emulatedserver.generated_from_json.Station;

/**
 * The bike stations emulated REST server.
 */

@Path("/stations")
public final class StationsRest {
	/**
	 * read all the stations from Paris
	 * 
	 * @return the list of stations in Paris
	 */
	
	private String fileName = "src/main/resources/paris.json";
	private Stations stations;
	
	private void JsonToJV(final String fileName) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		List<Station> stationList = Arrays.asList(mapper.readValue(new File(fileName), Station[].class));
		stations = new Stations(stationList);
	}
	@GET
	@Path("/all")
	@Produces(MediaType.TEXT_PLAIN)
	public String getAllStations() throws IOException {
		JsonToJV(fileName);
		return stations.toString();
	}
	
	
	/**
	 * read all the properties from a specific station in Paris
	 * 
	 * @param number
	 * 
	 * @return the list of properties from give id
	 */
	@GET
	@Path("/search/{number}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getOneStation(@PathParam("number") final long number) throws IOException {
		JsonToJV(fileName);
		return stations.lookupId(number).toString();
		
	}
}
