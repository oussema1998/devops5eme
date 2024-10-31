FROM openjdk:17-jdk-alpine
EXPOSE 8082

# Téléchargement du fichier JAR avec le bon chemin
RUN wget -O tpFoyer-17-0.0.1-SNAPSHOT.jar http://192.168.100.189:8081/repository/maven-snapshots/tn/esprit/tpFoyer-17/0.0.1-SNAPSHOT/tpFoyer-17-0.0.1-20241029.105248-1.jar

# Exécution de l'application
ENTRYPOINT ["java", "-jar", "/tpFoyer-17-0.0.1-SNAPSHOT.jar"]
