java -jar /app/planes/airplanes-1.0-SNAPSHOT.jar \
    & sleep 10s \
    && java -jar /app/planeModels/plane-models-1.0-SNAPSHOT.jar \
    & java -jar /app/gateway/gateway-1.0-SNAPSHOT.jar & nginx -g 'daemon off;'