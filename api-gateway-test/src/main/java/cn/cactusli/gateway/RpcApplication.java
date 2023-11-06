package cn.cactusli.gateway;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Package: cn.cactusli.gateway
 * Description:
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/2 16:43
 * @Github https://github.com/lixuanfengs
 */
@SpringBootApplication
@Configurable
@EnableDubbo
public class RpcApplication {

    public static void main(String[] args) {
        SpringApplication.run(RpcApplication.class, args);
    }
}
