FROM openjdk:11-jdk-slim

 ENV LANG C.UTF-8

ADD SpeciesAndEndowments-swarm.jar /opt/app.jar

EXPOSE 8090

ENTRYPOINT java $JAVA_OPTS -jar   /opt/app.jar 
