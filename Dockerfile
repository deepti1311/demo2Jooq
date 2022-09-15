FROM openjdk:11
WORKDIR /app
COPY . .
#RUN chown -R tntra.tntra /app
#USER tntra
RUN ["sh", "-c", "./gradlew clean build -x test"]

ARG DOCKER_IMAGE_TAG
ENV DOCKER_IMAGE_TAG $DOCKER_IMAGE_TAG

#USER tntra
WORKDIR /app
COPY --from=0 /app/build/libs/springBootcamp-*.jar app.jar
EXPOSE 9000

HEALTHCHECK --interval=30s --timeout=3s \
  CMD curl -f http://localhost:9000/health || exit 1

ENTRYPOINT ["sh", "-c", "java -jar $JAVABOOTCAMP_OPTS -Dspring.profiles.active=$SPRING_PROFILES_ACTIVE -Dsun.net.http.allowRestrictedHeaders=true app.jar"]