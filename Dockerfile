FROM amazoncorretto:11.0.20
COPY build/libs/dev-0.0.1.jar study.jar
ENTRYPOINT ["java", "-jar","study.jar", "-Ddb.id=${DATABASE_USER}", "-Ddb.pw=${DATABASE_PASSWORD}"]