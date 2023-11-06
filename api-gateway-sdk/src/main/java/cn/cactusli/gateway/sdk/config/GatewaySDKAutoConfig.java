package cn.cactusli.gateway.sdk.config;

import cn.cactusli.gateway.sdk.application.GatewaySDKApplication;
import cn.cactusli.gateway.sdk.domain.service.GatewayCenterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Package: cn.cactusli.gateway.sdk.config
 * Description:
 *  网关SDK配置服务
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/8/29 16:40
 * @Github https://github.com/lixuanfengs
 */
@Configuration
@EnableConfigurationProperties(GatewaySDKServiceProperties.class)
@ConditionalOnProperty(
        prefix = "gateway.sdk",
        name =  "enabled",
        havingValue = "true",
        matchIfMissing = true
)
public class GatewaySDKAutoConfig {

    private Logger logger = LoggerFactory.getLogger(GatewaySDKAutoConfig.class);

    @Bean
    public GatewayCenterService gatewayCenterService() {
        return new GatewayCenterService();
    }

    @Bean
    public GatewaySDKApplication gatewaySDKApplication(GatewaySDKServiceProperties properties, GatewayCenterService gatewayCenterService) {
        return new GatewaySDKApplication(properties, gatewayCenterService);
    }

}
