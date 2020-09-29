# build image
FROM gradle as BUILD_IMAGE
ENV APP_HOME=/usr/app
WORKDIR $APP_HOME

COPY gradle $APP_HOME/gradle
COPY --chown=gradle:gradle . /home/gradle/src
USER root
RUN chown -R gradle /home/gradle/src

RUN gradle build || return 0
COPY . .
RUN gradle clean build -x test

# run image
FROM java:8-jre-alpine
ENV APP_HOME=/usr/app
ENV ARTIFACT_ID=schachfish-0.0.4.jar

WORKDIR $APP_HOME
COPY --from=BUILD_IMAGE $APP_HOME/build/libs/$ARTIFACT_ID $APP_HOME/$ARTIFACT_ID

ENV JAVA_HOME=/usr/lib/jvm/java-1.8-openjdk
ENV PATH="$JAVA_HOME/bin:${PATH}"

ENTRYPOINT java -jar $APP_HOME/$ARTIFACT_ID
