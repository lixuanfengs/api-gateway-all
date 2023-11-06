#start.sh1
docker run -p 8018:8018 -p 7399:7399 \
	-e JAVA_OPTS="--add-opens=java.base/java.lang=ALL-UNNAMED" \
    -e PARAMS="--server.port=8018 --api-gateway.address=http://192.168.1.19:8001 --api-gateway.groupId=10001 --api-gateway.gatewayId=api-gateway-g4 --api-gateway.gatewayName=电商配送网关 --api-gateway.gatewayAddress=192.168.1.19:7399" \
    --name api-gateway-engine-01 -d api-gateway-engine:1.0.0


#start.sh2
docker run -p 8019:8019 -p 7398:7398 \
    -e JAVA_OPTS="--add-opens=java.base/java.lang=ALL-UNNAMED" \
    -e PARAMS="--server.port=8019 --api-gateway.address=http://192.168.1.19:8001 --api-gateway.groupId=10001 --api-gateway.gatewayId=api-gateway-g5 --api-gateway.gatewayName=电商配送网关 --api-gateway.gatewayAddress=192.168.1.19:7398" \
    --name api-gateway-engine-02 -d api-gateway-engine:1.0.0