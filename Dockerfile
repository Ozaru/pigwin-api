FROM openjdk:17-jdk-slim-buster

RUN apt-get update -y
RUN apt-get install -y git

WORKDIR /app

RUN git config --global core.autocrlf input

RUN git clone https://github.com/Ozaru/pigwin-api.git

WORKDIR /app/pigwin-api

RUN chmod +x ./gradlew
RUN ./gradlew bootJar

COPY build/libs/*.jar /app/app.jar

WORKDIR /app

RUN rm -rf pigwin-api

ENTRYPOINT java -jar app.jar