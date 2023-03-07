FROM maven:3.9.0-eclipse-temurin-17 as build

COPY src /usr/src/app/src
COPY pom.xml /usr/src/app

RUN mvn -f /usr/src/app/pom.xml clean package

FROM eclipse-temurin:17
COPY --from=build /usr/src/app/target/patient-management-0.0.1-SNAPSHOT.jar /usr/app/patient-management-0.0.1-SNAPSHOT.jar 

EXPOSE 8080  
ENTRYPOINT ["java","-jar","/usr/app/patient-management-0.0.1-SNAPSHOT.jar"]  