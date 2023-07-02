#!/bin/bash

APP_NAME="proto-food-api"

JAR="/app/target/proto-food-api-*\.jar"
JAR_FILE=$(ls $JAR | grep -v "sources\.jar$" | grep -v "javadoc\.jar$" 2>/dev/null)

JAVA_MEMORY_SETTING="-Xms512M -Xmx1024M"

JVM_GC_OPTIONS="-XX:+UseG1GC -XX:+PrintFlagsFinal -XX:ErrorFile=/logs/$APP_NAME/HS_ERR_PID%p.log"
JVM_GC_OPTIONS="$JVM_GC_OPTIONS -Xlog:gc*,safepoint:file=/logs/$APP_NAME/gc.log:utctime:filecount=10,filesize=10M"

CMD="java $JAVA_MEMORY_SETTING $JVM_GC_OPTIONS -jar $JAR_FILE"

exec $CMD