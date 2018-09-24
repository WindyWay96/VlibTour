#!/bin/bash

ARGS=$*

PATHSEP=':'

COMMONLOGGING=${HOME}/.m2/repository/commons-logging/commons-logging/1.1.1/commons-logging-1.1.1.jar

LOG4J=${HOME}/.m2/repository/log4j/log4j/1.2.17/log4j-1.2.17.jar

GEOCALC=${HOME}/.m2/repository/com/grum/geocalc/0.5.1/geocalc-0.5.1.jar
JXMAPVIEWER=${HOME}/.m2/repository/org/jxmapviewer/jxmapviewer2/2.2/jxmapviewer2-2.2.jar
OPENSTREEPMAP=${HOME}/.m2/repository/org/openstreetmap/jmapviewer/jmapviewer/2.3/jmapviewer-2.3.jar

VLIBTOUR=${HOME}/.m2/repository/eu/telecomsudparis/vlibtour
VLIBTOURVISITEMULATION=${VLIBTOUR}/libraries-for-vlibtour/vlibtour-visit-emulation/1.0-SNAPSHOT/vlibtour-visit-emulation-1.0-SNAPSHOT.jar

CLASSPATH=${CLASSPATH}${PATHSEP}${COMMONLOGGING}${PATHSEP}${LOG4J}${PATHSEP}${GEOCALC}${PATHSEP}${JXMAPVIEWER}${PATHSEP}${OPENSTREEPMAP}${PATHSEP}${VLIBTOURVISITEMULATION}

CLASS=vlibtour.vlibtour_map_viewer.MapDemo

# Start the demo
CMD="java -cp ${CLASSPATH} ${CLASS} ${ARGS}"

$CMD
