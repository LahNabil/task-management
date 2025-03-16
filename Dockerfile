FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-alpine
WORKDIR /app
COPY --from=build /app/target/Task-management-0.0.1-SNAPSHOT.jar ./demo-aws.jar
COPY /etc/letsencrypt/live/taskmanagementapi.me/fullchain.pem /etc/ssl/certs/
COPY /etc/letsencrypt/live/taskmanagementapi.me/privkey.pem /etc/ssl/private/
EXPOSE 8080 443
#CMD ["java", "-jar", "demo-aws.jar"]
#CMD ["java", "-Dserver.ssl.key-store=/etc/ssl/private/privkey.pem", "-Dserver.ssl.key-store-password=your-password", "-Dserver.ssl.key-alias=your-key-alias", "-jar", "demo-aws.jar"]
CMD ["java", "-Dserver.ssl.key-store=/etc/ssl/private/privkey.pem", \
    "-Dserver.ssl.key-store-password=your-password", \
    "-Dserver.ssl.key-alias=your-key-alias", \
    "-jar", "demo-aws.jar"]