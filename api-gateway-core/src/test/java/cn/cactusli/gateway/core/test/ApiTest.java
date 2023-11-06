package cn.cactusli.gateway.core.test;

import cn.cactusli.gateway.core.mapping.HttpCommandType;
import cn.cactusli.gateway.core.mapping.HttpStatement;
import cn.cactusli.gateway.core.session.Configuration;
import cn.cactusli.gateway.core.session.defaults.DefaultGatewaySessionFactory;
import cn.cactusli.gateway.core.socket.GatewaySocketServer;
import io.netty.channel.Channel;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Package: cn.cactusli.gateway
 * Description:
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/1 15:25
 * @Github https://github.com/lixuanfengs
 */
public class ApiTest {

    private final Logger logger = LoggerFactory.getLogger(ApiTest.class);

    /**
     * 测试：
     * http://localhost:8899/cactus/activity/sayHello
     * 参数：
     * {
     *     "str": "10008"
     * }
     *
     * http://localhost:8899/cactus/activity/insert
     * 参数：
     * {
     *     "name":"仙人球",
     *     "uid":"10008"
     * }
     */
    @Test
    public void test_gateway() throws ExecutionException, InterruptedException {
        // 1. 创建配置信息加载注册
        Configuration configuration = new Configuration();
        configuration.setHostName("127.0.0.1");
        configuration.setPort(8059);

        // 2. 基于配置构建会话工厂
        DefaultGatewaySessionFactory defaultGatewaySessionFactory = new DefaultGatewaySessionFactory(configuration);

        // 3. 创建启动网关网络服务
        GatewaySocketServer socketServer = new GatewaySocketServer(configuration, defaultGatewaySessionFactory);

        Future<Channel> future = Executors.newFixedThreadPool(2).submit(socketServer);
        Channel channel = future.get();

        if (null == channel) throw new RuntimeException("netty server start error channel is null");

        while (!channel.isActive()) {
            logger.info("netty server gateway start Ing ...");
            Thread.sleep(500);
        }
        logger.info("netty server gateway start Done! {}", channel.localAddress());

        // 4. 注册接口
        configuration.registryConfig("api-gateway-test","zookeeper://192.168.1.107:2181","cn.cactusli.gateway.rpc.IActivityBooth","1.0.0");

        HttpStatement httpStatement01 = new HttpStatement(
                "api-gateway-test",
                "cn.cactusli.gateway.rpc.IActivityBooth",
                "sayHello",
                "java.lang.String",
                "/cactus/activity/sayHello",
                HttpCommandType.GET,
                false);
        configuration.addMapper(httpStatement01);

        HttpStatement httpStatement02 = new HttpStatement(
                "api-gateway-test",
                "cn.cactusli.gateway.rpc.IActivityBooth",
                "insert",
                "cn.cactusli.gateway.rpc.dto.XReq",
                "/cactus/activity/insert",
                HttpCommandType.POST,
                true);
        configuration.addMapper(httpStatement02);


        Thread.sleep(Long.MAX_VALUE);
    }

}
