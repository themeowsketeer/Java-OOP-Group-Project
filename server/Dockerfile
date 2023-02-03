FROM ibm-semeru-runtimes:open-17-jre-focal
COPY target/server-0.0.1-SNAPSHOT.jar server-0.0.1-SNAPSHOT.jar
ENV _JAVA_OPTIONS="-XX:MaxRAM=70m"
CMD java $_JAVA_OPTIONS -Dspring.profiles.active=$SPRING_PROFILES_ACTIVE -Dspring.datasource.url=$SPRING_DATASOURCE_URL -Dspring.datasource.username=$SPRING_DATASOURCE_USERNAME -Dspring.datasource.password=$DB_POSTGRES_PASSWORD -jar server-0.0.1-SNAPSHOT.jar