FROM adoptopenjdk:14-jre-hotspot
RUN mkdir /app-hramba
WORKDIR /app-hramba
ADD ./api/target/api-1.0.0-SNAPSHOT.jar /app-hramba
EXPOSE 8081
ENTRYPOINT ["java","-jar","api-1.0.0-SNAPSHOT.jar"]


#docker login
#docker push username/name:version