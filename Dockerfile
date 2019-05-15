FROM gradle:5.4-jdk8 as build
COPY --chown=gradle:gradle . /tmp/
WORKDIR /tmp/
RUN ./gradlew clean build

FROM openjdk:8u201-jre-alpine as production
EXPOSE 8080
RUN mkdir /app
COPY --from=build /tmp/build/libs/service*.jar /app/application.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/application.jar"]