# base image present in Docker Hub
FROM openjdk:8
# add the spring boot jar to the container
ADD build/libs/users-mysql-0.1.0.jar users-mysql.jar
# expose the port (configured on app.props server port)
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "users-mysql.jar"]