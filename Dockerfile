FROM openjdk:17
ADD target/salsabilhamraoui-G6-GestionParking.jar salsabilhamraoui-G6-GestionParking.jar
ENTRYPOINT ["java","-jar","/salsabilhamraoui-G6-GestionParking.jar"]
