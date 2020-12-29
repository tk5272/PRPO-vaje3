FROM adoptopenjdk:14-jre-hotspot
RUN mkdir /app-sledilnik
WORKDIR /app-sledilnik
ADD ./api/target/api-1.0.0-SNAPSHOT.jar /app-hramba
EXPOSE 8080
ENTRYPOINT ["java","-jar","api-1.0.0-SNAPSHOT.jar"]


#docker login
#docker push username/name:version