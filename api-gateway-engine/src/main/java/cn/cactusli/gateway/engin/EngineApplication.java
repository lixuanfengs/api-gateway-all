package cn.cactusli.gateway.engin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Package: cn.cactusli.gateway.engin
 * Description:
 *  启动服务
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/8/25 17:16
 * @Github https://github.com/lixuanfengs
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class EngineApplication {

    public static void main(String[] args) {
        SpringApplication.run(EngineApplication.class, args);
    }
}
