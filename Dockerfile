## ---- Stage 1: Build the JAR with Gradle ----
#FROM gradle:8.5-jdk21 AS builder
#
#WORKDIR /app
#
## Copy Gradle build files
#COPY build.gradle* settings.gradle* ./
#COPY gradle ./gradle
#
## Pre-download dependencies
#RUN gradle build --no-daemon || true
#
## Copy source code
#COPY . .
#
## Build the app with detailed logs
#RUN gradle clean build --no-daemon --stacktrace --info
#
## ---- Stage 2: Create minimal runtime image ----
#FROM openjdk:21
#
#WORKDIR /app
#
#EXPOSE 8489
#
## Copy the built JAR into the runtime image
#COPY --from=builder /app/build/libs/appointment-service.jar ./appointment-service.jar
#
## Run the app
#ENTRYPOINT ["java", "-jar", "/app/appointment-service.jar"]
#




FROM openjdk:21
EXPOSE 8489
ADD build/libs/appointment-service.jar appointment-service.jar
ENTRYPOINT [\
"java",\
 "-jar", \
 "/appointment-service.jar"\
 ]
