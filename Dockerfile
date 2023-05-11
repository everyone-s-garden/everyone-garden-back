FROM adoptopenjdk/openjdk11

MAINTAINER JINKYUM PARK
WORKDIR ./
EXPOSE 80

ARG API_MAFRA_SECRET

ENV BUILD_DIR=./build/libs/*.jar
ENV API_MAFRA_SECRET=$API_MAFRA_SECRET
ENV SPRING_PROFILES_ACTIVE=db,oauth,api,prod,aws

COPY ${BUILD_DIR} ./garden.jar
ENTRYPOINT ["java", "-jar", "./garden.jar"]