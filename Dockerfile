# Stage 1: compile & package
FROM gradle:7.6-jdk21 AS builder
WORKDIR /home/app
COPY . .
RUN gradle clean bootJar --no-daemon

# Stage 2: runtime
FROM openjdk:21-jdk-slim
EXPOSE 8489
COPY --from=builder /home/app/build/libs/*.jar appointment-service.jar
ENTRYPOINT ["java","-jar","/appointment-service.jar"]




#FROM openjdk:21
#EXPOSE 8489
#ADD build/libs/appointment-service.jar appointment-service.jar
#ENTRYPOINT [\
#"java",\
# "-jar", \
# "/appointment-service.jar"\
# ]
