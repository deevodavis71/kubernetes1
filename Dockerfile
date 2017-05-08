FROM sjd300671/minimal-java
VOLUME /tmp
EXPOSE 8080
ADD target/kubernetes1-0.0.2-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
