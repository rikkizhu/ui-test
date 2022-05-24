# Base docker image
FROM selenium/standalone-chrome

USER root

# Install JRE 1.8 and maven
RUN apt-get update -y && apt install  openjdk-8-jdk  maven -y

USER seluser