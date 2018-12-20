# From java image, version : 8
FROM java:8

# 挂载app目录
VOLUME /app

# COPY or ADD to image
COPY Spring-Boot-Web-1.0-SNAPSHOT.jar app.jar

RUN bash -c "touch /app.jar"
EXPOSE 9999
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]
