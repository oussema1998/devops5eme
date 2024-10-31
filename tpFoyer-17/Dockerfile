FROM openjdk:17-jdk-alpine
EXPOSE 8082

# Téléchargement du livrable depuis Nexus
RUN wget -O tpFoyer-17-0.0.1-SNAPSHOT.jar http://192.168.100.15:8081/repository/maven-snapshots/tn/esprit/tpFoyer-17/0.0.1-SNAPSHOT/tpFoyer-17-0.0.1-20241031.005603-1.jar



# Exécution de l'application
ENTRYPOINT ["java", "-jar", "/tpFoyer-17-0.0.1-SNAPSHOT.jar"]