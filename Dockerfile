# Stage 1: Build
FROM maven:3.8.4-openjdk-17-slim AS build

# Create and set the working directory
WORKDIR /app

# Copy source code
COPY . .

# Build the application
RUN mvn clean install -DskipTests

# Stage 2: MySQL Initialization
FROM mysql/mysql-server:8.0.17 AS mysql-init

# Expose MySQL port
EXPOSE 3306

# Environment variables for MySQL
ENV MYSQL_ROOT_PASSWORD=111093
ENV MYSQL_DATABASE=todolist
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=111093

# Copy MySQL initialization scripts
COPY mysql-scripts/ /docker-entrypoint-initdb.d/

# Stage 3: Runtime
FROM openjdk:17-jdk-slim

# Create and set the working directory
WORKDIR /app

# Copy JAR from the build stage
COPY --from=build /app/target/todo-list-1.0.0.jar app.jar

# Expose Spring Boot application port
EXPOSE 8080

# Entrypoint command with script to wait for MySQL
COPY wait-for-mysql.sh /app/wait-for-mysql.sh
RUN chmod +x /app/wait-for-mysql.sh
CMD ["./wait-for-mysql.sh", "mysql-init", "java", "-jar", "app.jar"]
