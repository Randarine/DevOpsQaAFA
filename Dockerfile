FROM openjdk:17

# Set the working directory in the container
WORKDIR /AFDevOpsQA

# Copy the JAR file into the container at /educacaoGamificada
COPY target/*.jar /AFDevOpsQA/AFDevOpsQA-0.0.1-SNAPSHOT.jar

# Expose the port that your application will run on
EXPOSE 8585

# Specify the command to run on container start
CMD ["java", "-jar", "AFDevOpsQA-0.0.1-SNAPSHOT.jar"]