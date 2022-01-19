FROM nginx:alpine

RUN apk update && apk add bash

#  default conf for proxy service
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Proxy configurations
#COPY ./includes/ /etc/nginx/includes/

EXPOSE 80
EXPOSE 8080