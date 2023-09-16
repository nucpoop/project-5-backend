FROM amazoncorretto:11.0.20
COPY build/libs/*.jar study.jar
ENTRYPOINT ["java", "-jar","study.jar", "-Dspring-boot.run.arguments=--db.id=${DATABASE_USER}, --db.pw=${DATABASE_PASSWORD}"]