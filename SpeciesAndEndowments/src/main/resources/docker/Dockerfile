FROM openjdk:11-jdk-slim

 ENV LANG C.UTF-8

ADD ${project.artifactId}-swarm.jar /opt/app.jar

EXPOSE 8090

ENTRYPOINT java $JAVA_OPTS -jar   /opt/app.jar 
