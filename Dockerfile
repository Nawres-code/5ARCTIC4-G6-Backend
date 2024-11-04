FROM openjdk:17
ADD target/springboot-mysql-docker.jar 5Arctic4-G6-GestionParking.jar
ENTRYPOINT ["java","-jar","/5Arctic4-G6-GestionParking.jar"]