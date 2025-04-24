# Stage 1: build the JAR using the Gradle wrapper
FROM gradle:7.6-jdk21 AS builder
WORKDIR /home/app

# Copy only the wrapper and build scripts, so we cache JVM downloads & deps
COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle.kts settings.gradle.kts ./

# Make the wrapper executable
RUN chmod +x gradlew

# Pre-fetch dependencies & compile
RUN ./gradlew clean bootJar --no-daemon -x test

# Stage 2: runtime image
FROM openjdk:21-jdk-slim
EXPOSE 8489

# Copy the fat JAR from the builder stage
COPY --from=builder /home/app/build/libs/*.jar /appointment-service.jar

ENTRYPOINT ["java","-jar","/appointment-service.jar"]



#FROM openjdk:21
#EXPOSE 8489
#ADD build/libs/appointment-service.jar appointment-service.jar
#ENTRYPOINT [\
#"java",\
# "-jar", \
# "/appointment-service.jar"\
# ]
