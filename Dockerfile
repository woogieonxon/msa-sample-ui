 # Start with a base image containing Gradle 8.5 with JDK 8
FROM gradle:8.5-jdk8 as build

# Set the working directory inside the container
WORKDIR /app

# Copy Gradle Wrapper files to avoid downloading them during the Docker build
COPY gradle/wrapper/gradle-wrapper.jar gradle/wrapper/gradle-wrapper.jar
COPY gradle/wrapper/gradle-wrapper.properties gradle/wrapper/gradle-wrapper.properties

# Copy the rest of the Gradle wrapper files and project files
COPY gradlew gradlew
COPY gradlew.bat gradlew.bat
COPY gradle gradle
COPY build.gradle settings.gradle /app/
COPY src /app/src

# Grant execute permission for the gradlew script
RUN chmod +x gradlew

# Build the project
RUN gradle clean build

# Step 2: Use a lightweight OpenJDK 8 image to run the application
FROM openjdk:8-jdk-alpine


# Copy the JAR file from the build stage
COPY --from=build /app/build/libs/*.jar msa-sample-ui.jar

# Command to run the application
ENTRYPOINT ["java", "-jar", "msa-sample-ui.jar"]