# 基础镜像
FROM openjdk:17.0.2-slim
# 作者
LABEL maintainer="cactusli"
LABEL description="Docker image for API Gateway Engine"
LABEL version="1.0.0"
# 配置
ENV PARAMS=""
# 时区
ENV TZ=PRC
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
# 添加应用
ADD target/api-gateway-engine-*.jar /api-gateway-engine.jar
# 执行镜像
ENTRYPOINT ["sh","-c","java -jar $JAVA_OPTS /api-gateway-engine.jar $PARAMS"]