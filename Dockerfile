FROM openjdk:17

RUN mkdir -p /app
RUN mkdir -p /logs/proto-food-api

COPY ./target/proto-food-api-*.jar /app/target/

ADD ./run.sh /app/run/run.sh
RUN chmod +x /app/run/run.sh

ENTRYPOINT ["/bin/bash", "-c", "/app/run/run.sh"]