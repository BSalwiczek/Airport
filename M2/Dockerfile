FROM nginx:alpine

RUN apk update && apk add bash
COPY nginx.conf /etc/nginx/conf.d/default.conf
RUN mkdir /etc/nginx/sites-enabled/
RUN ln -s /etc/nginx/sites-available/fontend.conf /etc/nginx/sites-enabled

RUN mkdir /app/
RUN mkdir /app/gateway
RUN mkdir /app/planes
RUN mkdir /app/planeModels

COPY "gateway/target/" /app/gateway
COPY "planes/target/" /app/planes
COPY "planeModels/target/" /app/planeModels

# install java
RUN apk add openjdk11

COPY run.sh /
CMD sh /run.sh
