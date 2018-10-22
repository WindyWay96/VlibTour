#!/bin/bash

export MAVEN_REPOS=${HOME}/.m2/repository

export PATHSEP=':'

export RABBITMQ_CLIENT_VERSION=5.4.1
export AMQPCLIENT=${MAVEN_REPOS}/com/rabbitmq/amqp-client/${RABBITMQ_CLIENT_VERSION}/amqp-client-${RABBITMQ_CLIENT_VERSION}.jar
export SL4J=${MAVEN_REPOS}/org/slf4j/slf4j-api/1.7.25/slf4j-api-1.7.25.jar
export SL4JSIMPLE=${MAVEN_REPOS}/org/slf4j/slf4j-simple/1.7.25/slf4j-simple-1.7.25.jar

export COMMONLANG3=${MAVEN_REPOS}/org/apache/commons/commons-lang3/3.6/commons-lang3-3.6.jar
export COMMONLOGGING=${MAVEN_REPOS}/commons-logging/commons-logging/1.1.1/commons-logging-1.1.1.jar

export LOG4J=${MAVEN_REPOS}/log4j/log4j/1.2.17/log4j-1.2.17.jar

export GEOCALC=${MAVEN_REPOS}/com/grum/geocalc/0.5.1/geocalc-0.5.1.jar
export JXMAPVIEWER=${MAVEN_REPOS}/org/jxmapviewer/jxmapviewer2/2.2/jxmapviewer2-2.2.jar
export OPENSTREEPMAP=${MAVEN_REPOS}/org/openstreetmap/jmapviewer/jmapviewer/2.3/jmapviewer-2.3.jar

export VLIBTOUR=${MAVEN_REPOS}/eu/telecomsudparis/vlibtour

export TOURMANAGEMENTENTITY=${VLIBTOUR}/vlibtour-tour-management/vlibtour-tour-management-entity/1.0-SNAPSHOT/vlibtour-tour-management-entity-1.0-SNAPSHOT.jar
export TOURMANAGEMENTAPI=${VLIBTOUR}/vlibtour-tour-management/vlibtour-tour-management-api/1.0-SNAPSHOT/vlibtour-tour-management-api-1.0-SNAPSHOT.jar
export TOURMANAGEMENTBEAN=${VLIBTOUR}/vlibtour-tour-management/vlibtour-tour-management-bean/1.0-SNAPSHOT/vlibtour-tour-management-bean-1.0-SNAPSHOT.jar

export LOBBYROOMAPI=${VLIBTOUR}/vlibtour-lobby-room-system/vlibtour-lobby-room-api/1.0-SNAPSHOT/vlibtour-lobby-room-api-1.0-SNAPSHOT.jar
export LOBBYROOMSERVER=${VLIBTOUR}/vlibtour-lobby-room-system/vlibtour-lobby-room-server/1.0-SNAPSHOT/vlibtour-lobby-room-server-1.0-SNAPSHOT.jar

export CLIENTLOBBYROOM=${VLIBTOUR}/vlibtour-client/vlibtour-client-lobby-room/1.0-SNAPSHOT/vlibtour-client-lobby-room-1.0-SNAPSHOT.jar
export CLIENTGROUPCOMMSYSTEM=${VLIBTOUR}/vlibtour-client/vlibtour-client-group-communication-system/1.0-SNAPSHOT/vlibtour-client-group-communication-system-1.0-SNAPSHOT.jar

export SCENARIO=${VLIBTOUR}/vlibtour-scenario/1.0-SNAPSHOT/vlibtour-scenario-1.0-SNAPSHOT.jar

export UTILPASSAY=${MAVEN_REPOS}/org/passay/passay/1.3.0/passay-1.3.0.jar

export VISITEMULATION=${VLIBTOUR}/libraries-for-vlibtour/vlibtour-visit-emulation/1.0-SNAPSHOT/vlibtour-visit-emulation-1.0-SNAPSHOT.jar
