# Sample project
Diplaying usage of spring Boot with Vue.js

# Stack

## Backend
* Spring Boot 2.4.5
* Spring Web
 
### Data base 
* SQLite

### Other libraries  
* Lombok
* Mapstruct
## Front End
* Vue.js (3)
* Bootstrap 5
## Packaging
Packaged as single jar, Embedded Tomcat will serve both spring and Vue project from static files  
Using frontend-maven-plugin

## USage 
* mvn clean package
*  cd target
* java -jar phoneNumberValidtor-0.0.1-SNAPSHOT.jar
* open http://localhost:12345/