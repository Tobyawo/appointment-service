# ---- Stage 1: Build the JAR with Gradle ----
FROM gradle:8.5-jdk21 AS builder

WORKDIR /app

# Copy only Gradle build files first for layer caching
COPY build.gradle* settings.gradle* ./
COPY gradle ./gradle

# Download dependencies
RUN gradle build --no-daemon || true

# Copy source code
COPY . .

# Build the fat JAR
RUN gradle clean build --no-daemon

# ---- Stage 2: Create minimal runtime image ----
FROM openjdk:21

WORKDIR /app

EXPOSE 8489

# Copy the built JAR from the builder stage
COPY --from=builder /app/build/libs/appointment-service.jar ./appointment-service.jar

ENTRYPOINT [ "java", "-jar", "/app/appointment-service.jar" ]




#FROM openjdk:21
#EXPOSE 8489
#ADD build/libs/appointment-service.jar appointment-service.jar
#ENTRYPOINT [\
#"java",\
# "-jar", \
# "/appointment-service.jar"\
# ]
