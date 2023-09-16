FROM amazoncorretto:11.0.20
COPY build/lib/*.jar study.jar
ENTRYPOINT ["java", "-jar","study.jar"]