FROM adoptopenjdk/openjdk11:alpine as build

WORKDIR /workspace/app

COPY mvnw .

COPY .mvn .mvn

COPY pom.xml .

COPY src src

RUN ./mvnw install -DskipTests

RUN ls -lsa target

FROM adoptopenjdk/openjdk11:alpine-jre

COPY --from=build /workspace/app/target/*.jar app.jar

ENTRYPOINT ["sh", "-c", "java -jar /app.jar"]