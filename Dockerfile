# Step 1: Use an official Java runtime as a base image
FROM openjdk:17-jdk-alpine

# Step 2: Set the working directory inside the container
WORKDIR /app

# Step 3: Copy the JAR file from the host to the container
COPY target/LibrarySystem-0.0.1-SNAPSHOT.jar app.jar

# Step 4: Expose the port your application runs on
EXPOSE 8080

# Step 5: Specify the command to run your app
ENTRYPOINT ["java", "-jar", "app.jar"]
