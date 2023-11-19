# Stage 1: Build
FROM ubuntu:latest AS build

# Install JDK
RUN apt-get update && \
    apt-get install openjdk-17-jdk -y

# Install Maven
RUN apt-get install maven -y

# Copy source code
COPY . .

# Build the application
RUN mvn clean install -DskipTests

# Stage 2: Runtime
FROM mysql/mysql-server:8.0.17

# Expose MySQL port
EXPOSE 3306

# Environment variables for MySQL
ENV MYSQL_ROOT_PASSWORD=111093
ENV MYSQL_DATABASE=todolist
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=111093

# Copy MySQL initialization scripts
COPY mysql-scripts/ /docker-entrypoint-initdb.d/

# Stage 3: Final image with only JRE
FROM openjdk:17-jdk-slim

# Expose Spring Boot application port
EXPOSE 8080

# Copy JAR from the build stage
COPY --from=build /target/todo-list-1.0.0.jar app.jar

# Entrypoint command
CMD ["sh", "-c", "java -jar app.jar"]
