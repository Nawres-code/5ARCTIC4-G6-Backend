FROM openjdk:17
ADD target/springboot-mysql-docker.jar springboot-mysql-docker.jar
ENTRYPOINT ["java","-jar","/salsabilhamraoui-G6-GestionParking.jar"]