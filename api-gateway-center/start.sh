docker run -p 8001:8001 \
-v /opt/api-gateway/api-gateway-center/doc/data/nginx:/data/nginx \
-v /var/run/docker.sock:/var/run/docker.sock \
--name api-gateway-center \
-d 192.168.1.19/api-gateway/api-gateway-center:1.0.0 CP4-LISTEN:8001,fork,reuseaddr UNIX-CONNECT:/var/run/docker.sock TCP4-LISTEN:8001,fork,reuseaddr UNIX-CONNECT:/var/run/docker.sock