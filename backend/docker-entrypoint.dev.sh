#!/bin/sh

export TERM=xterm

mvn clean spring-boot:run -Dspring-boot.run.jvmArguments="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005" -X -e &

while true; do
    watch -d -t -g "ls -lR . | sha1sum" && mvn compile
done
