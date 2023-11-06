# 基础镜像
FROM openjdk:17.0.2-slim
# 作者
LABEL maintainer="cactusli"
LABEL description="Docker image for API Gateway Center"
LABEL version="1.0.1"
# 配置
ENV PARAMS=""
# 时区
ENV TZ=PRC
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
# 添加应用
ADD target/api-gateway-center-*.jar /api-gateway-center.jar
# 执行镜像
ENTRYPOINT ["sh","-c","java -jar $JAVA_OPTS /api-gateway-center.jar $PARAMS"]