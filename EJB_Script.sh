mvn clean install -f ./vlibtour-tour-management/pom.xml
mvn clean install -f ./vlibtour-client/vlibtour-admin-client-tour-management/pom.xml

./Scripts/utils.sh
./Scripts/admin_client_tour_management.sh empty toursAndPOIs

asadmin undeploy vlibtour-tour-management-bean
asadmin stop-database
asadmin stop-domain domain1

asadmin start-domain domain1
asadmin start-database
 
asadmin deploy vlibtour-tour-management/vlibtour-tour-management-bean/target/vlibtour-tour-management-bean.jar

./Scripts/utils.sh

./Scripts/admin_client_tour_management.sh populate toursAndPOIs
