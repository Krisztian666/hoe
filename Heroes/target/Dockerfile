FROM openjdk:11-jdk-slim

 ENV LANG C.UTF-8

ADD Heroes.jar /opt/app.jar

EXPOSE 8091

ENTRYPOINT java $JAVA_OPTS -jar   /opt/app.jar 
