package cn.cactusli.gateway.center;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Package: cn.cactusli.gateway.center
 * Description:
 *  网关注册中心启动服务
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/20 15:07
 * @Github https://github.com/lixuanfengs
 */
@SpringBootApplication
@Configurable
public class CenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(CenterApplication.class, args);
    }
}
