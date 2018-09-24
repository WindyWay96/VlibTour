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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class provides utility methods for the visit of tourists. You are free
 * to complement or modify this code.
 * 
 * The data structures are designed to allow the management of the visit of
 * several users.
 * 
 * A limitation of the current implementation is that there is only one graph of
 * positions. The limitation is imposed by the usage of the class
 * {@link vlibtour.vlibtour_visit_emulation.GraphOfPositionsForEmulation}.
 * 
 * @author Denis Conan
 */
public final class VisitManagementMethodsHelper {
	/**
	 * the visit of the users.
	 */
	private static Map<String, List<Position>> visits;
	/**
	 * the indices in the visits.
	 */
	private static Map<String, Integer> currentIndicesInVisits;

	static {
		visits = new HashMap<>();
		currentIndicesInVisits = new HashMap<>();
	}

	/**
	 * private constructor to avoid instantiation.
	 */
	private VisitManagementMethodsHelper() {
	}

	/**
	 * sets the visit for a group of users. The visit is a set of {@link Position};
	 * this is not a set in order to allow the visit of the same position several
	 * times in the same tour.
	 * 
	 * @param users the group of users as a set.
	 * @param visit the visit as a list of positions.
	 */
	public static synchronized void setVisit(final Set<String> users, final List<Position> visit) {
		if (users == null) {
			throw new IllegalArgumentException("group of users is null or empty");
		}
		for (String user : users) {
			visits.put(user, visit);
		}
	}

	/**
	 * gets the visit of a user.
	 * 
	 * @param user the identifier of the user
	 * @return the visit as a list of positions.
	 */
	public static synchronized List<Position> getVisit(final String user) {
		return visits.get(user);
	}

	/**
	 * starts visiting, that is computes the path to the first POI.
	 * 
	 * @param user the identifier of the user.
	 */
	public static synchronized void startVisit(final String user) {
		if (GraphOfPositionsForEmulation.getAdjacencySets() == null
				|| GraphOfPositionsForEmulation.getAdjacencySets().isEmpty()) {
			throw new IllegalStateException("There is no graph of positions");
		}
		if (visits.get(user) == null) {
			throw new IllegalArgumentException("user " + user + " has no visit set");
		}
		if (visits.get(user).size() == 0) {
			throw new IllegalArgumentException("the visit of user " + user + " is empty");
		}
		currentIndicesInVisits.put(user, 0);
		GraphOfPositionsForEmulation.setAPathTo(user, visits.get(user).get(0));
	}

	/**
	 * steps in the visit, that is computes the path to the next POI.
	 * 
	 * @param user the identifier of the user.
	 */
	public static synchronized void stepsInVisit(final String user) {
		if (GraphOfPositionsForEmulation.getAdjacencySets() == null
				|| GraphOfPositionsForEmulation.getAdjacencySets().isEmpty()) {
			throw new IllegalStateException("There is no graph of positions");
		}
		if (visits.get(user) == null) {
			throw new IllegalArgumentException("user " + user + " has no visit set");
		}
		if (visits.get(user).size() == 0) {
			throw new IllegalArgumentException("the visit of user " + user + " is empty");
		}
		if (currentIndicesInVisits.get(user) == null) {
			throw new IllegalArgumentException("user " + user + " has no current index in visit set");
		}
		if (currentIndicesInVisits.get(user) == visits.get(user).size() - 1) {
			System.out.println(user + ": already at the end of their visit (no new current path)");
		} else {
			currentIndicesInVisits.put(user, currentIndicesInVisits.get(user) + 1);
			GraphOfPositionsForEmulation.setAPathTo(user, visits.get(user).get(currentIndicesInVisits.get(user)));
		}
	}

	/**
	 * states whether the given user has arrived to the last POI of the visit.
	 * 
	 * @param user the identifier of the user.
	 * @return the boolean indicating whether the user is at the POI of the visit.
	 */
	public static synchronized boolean isAtLastPOIOfTheVisit(final String user) {
		if (GraphOfPositionsForEmulation.getAdjacencySets() == null
				|| GraphOfPositionsForEmulation.getAdjacencySets().isEmpty()) {
			throw new IllegalStateException("There is no graph of positions");
		}
		if (visits.get(user) == null) {
			throw new IllegalArgumentException("user " + user + " has no visit set");
		}
		if (visits.get(user).size() == 0) {
			throw new IllegalArgumentException("the visit of user " + user + " is empty");
		}
		return visits.get(user).get(visits.get(user).size() - 1)
				.equals(GraphOfPositionsForEmulation.getCurrentPosition(user));
	}
}
