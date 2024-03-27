#!/bin/bash

set -e

docker stop nginx_reverse

if certbot renew; then
  cp /etc/letsencrypt/live/barboss.tech/*.pem /home/bartender/nginx/certs/
  chown -R bartender:bartender /home/bartender/nginx/certs/
  echo "New certificates copied"
fi

docker start nginx_reverse
if docker exec nginx_reverse nginx -s reload; then
  echo "Nginx config reloaded"
fi