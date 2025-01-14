# Use the official OpenJDK image as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the entire project directory into the container
COPY . .

# List the contents of the /app directory for debugging
RUN ls -R

# Install Maven
RUN apt-get update && apt-get install -y maven

# Build the application
RUN mvn package -DskipTests

# Set the startup command to run your Spring Boot application
CMD ["java", "-jar", "target/prueba-tecnica-0.0.1-SNAPSHOT.jar"]
