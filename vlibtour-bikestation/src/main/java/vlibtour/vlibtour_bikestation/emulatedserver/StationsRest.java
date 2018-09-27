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

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

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
	@GET
	public String getAllStations() {
		
		//TODO
		
		return "";
	}
	
	
	/**
	 * read all the properties from a specific station in Paris
	 * 
	 * @param id
	 * 
	 * @return the list of properties from give id
	 */
	@GET
	@Path("{id}")
	public String getOneStation(@PathParam(value = "id") int id) {
		
		//TODO
		
		return "";
	}
}
