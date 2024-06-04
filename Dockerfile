
ARG JDK_VERSION=17-jdk-slim

FROM openjdk:${JDK_VERSION}

ENV APP_USER=app

RUN groupadd -r ${APP_USER} && useradd -r -g ${APP_USER} -m -d /home/${APP_USER} -s /sbin/nologin ${APP_USER}

WORKDIR /app

COPY . /app

RUN chown -R ${APP_USER}:${APP_USER} /app

USER ${APP_USER}

RUN ./mvnw clean package

EXPOSE 8080

CMD ["java", "-jar", "target/oceanstyle-0.0.1-SNAPSHOT.jar"]
