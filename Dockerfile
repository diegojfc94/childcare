FROM openjdk:11
VOLUME /tmp
ARG JAR_FILE=/build/libs/Childcare-1.2.3-RELEASE.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8008
ENTRYPOINT ["java","-jar","/app.jar"]