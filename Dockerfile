FROM eclipse-temurin:21
RUN mkdir /opt/app
WORKDIR /opt/app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /opt/app/app.jar
CMD ["java", "-jar", "/opt/app/app.jar"]
