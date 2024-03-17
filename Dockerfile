FROM openjdk:11

WORKDIR /lung-cancer-patients

COPY CancerPatients-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]
