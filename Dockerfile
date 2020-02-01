FROM openjdk:13-alpine3.9

COPY app.jar /app.jar

CMD java -jar /app.jar
