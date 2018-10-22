package vlibtour.vlibtour_client_scenario;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import vlibtour.vlibtour_visit_emulation.ExampleScenarioTouristsInParis;
import vlibtour.vlibtour_visit_emulation.Position;

/**
 * This class is the client application of the tourists. The access to the lobby
 * room server and then to the group communication system should be implemented
 * using the design pattern Delegation (see
 * https://en.wikipedia.org/wiki/Delegation_pattern).
 * 
 * A client creates two queues to receive messages from the broker; the binding
 * keys are of the form "{@code *.*.identity}" and "{@code *.*.location}" while
 * the routing keys are of the form "{@code sender.receiver.identity|location}".
 * The location is emulated using the methods of the class
 * {@link vlibtour.vlibtour_visit_emulation.GraphOfPositionsForEmulation}.
 * 
 * @author Denis Conan
 */
public class VLibTourVisitTouristApplication {
	/**
	 * the tour identifier. It is {@code null} as long as the
	 */
	@SuppressWarnings("unused")
	private static Optional<String> tourId = Optional.empty();
	/**
	 * paths followed from the beginning.
	 */
	private List<Position> pathFromBeginning;

	/**
	 * gets the path of this client application that the tourist has visited.
	 * 
	 * @return the path from the beginning of the visit.
	 */
	public String getPathFromBeginning() {
		return pathFromBeginning.stream().map(Position::getName).collect(Collectors.joining(","));
	}

	/**
	 * executes the tourist application.
	 * 
	 * @param args the command line arguments.
	 * @throws Exception all the potential problems (since this is a demonstrator,
	 *                   apply the strategy "fail fast").
	 */
	public static void main(final String[] args) throws Exception {
		@SuppressWarnings("unused")
		VLibTourVisitTouristApplication client = null;
		String usage = "USAGE: " + VLibTourVisitTouristApplication.class.getCanonicalName()
				+ " userId (either Joe or Avrel)";
		if (args.length != 1) {
			throw new IllegalArgumentException(usage);
		}
		String userId = args[0];
		System.out.println(userId + "'s application is starting");
		// the tour is empty, this client gets it from the scenario
		tourId = Optional.ofNullable(ExampleScenarioTouristsInParis
				.setTheVisitOfTheScenario(userId.equals(ExampleScenarioTouristsInParis.USER_ID_JOE) ? 1 : 2));
		// TODO
		System.out.println(
				userId + "'s application is exiting; path of the visit was " + "call to client.getPathFromBeginning()");
		System.exit(0); // TODO: solve end of all threads in order to remove this exit
	}
}
