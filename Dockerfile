#Multi-stage approach
FROM openjdk:8 AS TEMP_BUILD_IMAGE
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY build.gradle settings.gradle gradlew $APP_HOME
COPY gradle $APP_HOME/gradle
RUN ./gradlew build || return 0
COPY . .
RUN ./gradlew build



# base image present in Docker Hub
FROM openjdk:8
# add the spring boot jar to the container
ENV ARTIFACT_NAME=task-organizer-1.0.jar
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY --from=TEMP_BUILD_IMAGE $APP_HOME/build/libs/$ARTIFACT_NAME .

# expose the port (configured on app.props server port)
EXPOSE 8086
CMD exec java -jar $ARTIFACT_NAME