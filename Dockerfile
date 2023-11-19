FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y

RUN apt-get install mysql-server -y
RUN service mysql start --skip-grant-tables && \
    mysql -u root -e "ALTER USER 'root'@'localhost' IDENTIFIED WITH 'mysql_native_password' BY '111093'; FLUSH PRIVILEGES;" && \
    service mysql stop

EXPOSE 3306
CMD ["service", "mysql", "start"]

COPY . .

RUN apt-get install maven -y
RUN mvn clean install -DskipTests

FROM openjdk:17-jdk-slim
EXPOSE 8080

COPY --from=build /target/todo-list-1.0.0.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]