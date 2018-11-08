#!/bin/bash

mvn clean install -f ~/eclipse-workspace/phucVlib/VlibTour/vlibtour-tour-management/pom.xml
mvn clean install -f ~/eclipse-workspace/phucVlib/VlibTour/vlibtour-client/vlibtour-admin-client-tour-management/pom.xml
 asadmin undeploy vlibtour-tour-management-bean
  asadmin stop-database
  asadmin stop-domain domain1

  asadmin start-domain domain1
  asadmin start-database

asadmin deploy vlibtour-tour-management/vlibtour-tour-management-bean/target/vlibtour-tour-management-bean.jar

. ./Scripts/utils.sh
./Scripts/admin_client_tour_management.sh populate toursAndPOIs






