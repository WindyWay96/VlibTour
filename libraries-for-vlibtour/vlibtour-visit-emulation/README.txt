This module proposes generic methods to build the graph of positions as
a set of adjacency list and to search for paths from a departure node
to a destination node, and utility methods to manage the pathway in
the choosen path.

Here follows the content of the module
$ tree
.
├── pom.xml
└── src
    ├── main
    │   ├── java
    │   │   └── vlibtour
    │   │       ├── vlibtour_map_viewer
    │   │       │   ├── BasicMap.java
    │   │       │   ├── MapDemo.java
    │   │       │   ├── OfflineOsmTileSource.java
    │   │       │   └── UtilityForMap.java
    │   │       └── vlibtour_scenario_emulation
    │   │           ├── DepthFirstSearch.java
    │   │           ├── GPSPosition.java
    │   │           ├── GraphOfPositionsForEmulation.java
    │   │           ├── Log.java
    │   │           ├── Position.java
    │   │           └── VLibTourGeoLocationEmulator.java
    │   └── resources
    │       └── itineraries
    │           └── network_for_itinerary_with_three_pois.txt
    │       └── osm-mapnik
    │           ├── 13
    |           .   ├─...
    |           .   .
    |           .   .
    |           .   .
    |           .
    │           └── 14
    |               ├─...
    |               .
    |               .
    |               .
    └── test
        └── java
            └── vlibtour
                └── vlibtour_scenario_emulation
                    ├── TestDepthFirstSearch.java
                    ├── TestGPSPosition.java
                    └── TestVLibTourGeoLocationEmulator.java

Package vlibtour_map_viewer
---------------------------
The graphical interface comes from OpenStreetMap project.
The javadoc for this library is here  
https://josm.openstreetmap.de/doc/index.html?org/openstreetmap/gui/jmapviewer/package-summary.html

To run the demo : 
mvn clean install
mvn exec:java@map

Package vlibtour_scenario_emulation
-----------------------------------
The class Position defines the node of the graph for the emulation. A
position contains (by delegation) a GPSPosition. It is assumed that
some of the positions are Points Of Interest (POI). The class Position
then possesses a reference to an Object that will refer to your
version of the class POI.

The graph of positions is built in the method initTourWithPOIs of the
class GraphOfPositionsForEmulation. The class DepthFirstSearch
contains the generic method addEdgeToAdjacencySet for building the
graph as a set of adjacency sets and the generic method
computePathsFromDepartureToDestination for computing all the paths
from a departure position to a destination position. Finally, the
class VLibTourGeoLocationEmulator proposes methods that you can use in
the emulation for the visits: e.g. the methods setAdjacencySets for
initialising the graph, setStartingPosition for assigning the current
position of a user to a given position at the beginning of the
scenario, setAPathTo for computing and randomly choosing a path from
the current position to a destination position, and stepInCurrentPath
for stepping in the computed path. Please refer to the JUnit test
class TestVLibTourGeoLocationEmulator for a demonstration of these
methods and to the Javadoc documentation.
