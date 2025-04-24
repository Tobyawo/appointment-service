FROM openjdk:21
EXPOSE 8489
ADD build/libs/appointment-service.jar appointment-service.jar
ENTRYPOINT [\
"java",\
 "-jar", \
 "/appointment-service.jar"\
 ]
