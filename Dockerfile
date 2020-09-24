FROM java:8-jre-alpine
WORKDIR /usr/app
COPY build/libs/schachfish-0.0.1-SNAPSHOT.jar /usr/app/schachfish.jar
ENV JAVA_HOME=/usr/lib/jvm/java-1.8-openjdk
ENV PATH="$JAVA_HOME/bin:${PATH}"
EXPOSE 9100

CMD ["java", "-jar", "/usr/app/schachfish.jar"]