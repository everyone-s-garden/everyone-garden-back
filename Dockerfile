FROM adoptopenjdk/openjdk11

MAINTAINER JINKYUM PARK
WORKDIR ./
EXPOSE 80

ARG API_MAFRA_SECRET
ENV API_MAFRA_SECRET=$API_MAFRA_SECRET

ARG API_WEATHER_SECRET
ENV API_WEATHER_SECRET=$API_WEATHER_SECRET

ARG API_WEATHERWEEK_SECRET
ENV API_WEATHERWEEK_SECRET=$API_WEATHERWEEK_SECRET

ARG API_WEATHERALL_SECRET
ENV API_WEATHERALL_SECRET=$API_WEATHERALL_SECRET

ARG API_REGEO_ID
ENV API_REGEO_ID=$API_REGEO_ID

ARG API_REGEO_SECRET
ENV API_REGEO_SECRET=$API_REGEO_SECRET

ENV BUILD_DIR=./build/libs/*.jar
ENV SPRING_PROFILES_ACTIVE=db,oauth,api,prod,aws

COPY ${BUILD_DIR} ./garden.jar
ENTRYPOINT ["java", "-jar", "./garden.jar"]