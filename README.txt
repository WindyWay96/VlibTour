This is the case study "VlibTour".

Since Glassfish does not run under JAVA 9 or 10, but requires JAVA 1.8, we add
the shell script 'java8', which is acessible via the shell variable PATH:
$cat java8
#! /bin/bash
JAVA_HOME=...Jdk1.8.0_181 # adapt the path to your configuration
CLASSPATH=$JAVA_HOME/lib
export PATH CLASSPATH JAVA_HOME

To compile and install, execute the following command:
$ (. java8;mvn clean install)

To run the scenario of the demonstrator:
$ (. java8;./run_scenario_w_mapviewer.sh)
