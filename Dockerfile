# Use the official OpenJDK 17 as a base image
FROM openjdk:17-jdk-slim

# Set environment variables for MySQL
ENV MYSQL_ROOT_PASSWORD=111093
ENV MYSQL_DATABASE=todolist
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=111093

# Install MySQL
RUN apt-get update && \
    apt-get install -y mysql-server && \
    service mysql start --skip-grant-tables && \
    mysql -u root -e "ALTER USER 'root'@'localhost' IDENTIFIED WITH 'mysql_native_password' BY '111093'; FLUSH PRIVILEGES;" && \
    service mysql stop

# Set the working directory
WORKDIR /app

# Copy the JAR file
COPY target/todo-list-1.0.0.jar app.jar

# Expose the application port
EXPOSE 8080
EXPOSE 3306


# Command to run the application
CMD ["sh", "-c", "sleep 10 && service mysql start && java -jar app.jar"]
