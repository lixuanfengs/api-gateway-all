package cn.cactusli.gateway.center.domain.message;

import cn.cactusli.gateway.center.application.IMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Package: cn.cactusli.gateway.center.domain.message
 * Description:
 *  消息服务
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/9/14 14:56
 * @Github https://github.com/lixuanfengs
 */
@Service
public class IMessageServiceImpl implements IMessageService {

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private Integer port;
    @Value("${spring.redis.password}")
    private String password;
    @Resource
    private Publisher publisher;

    @Override
    public Map<String, String> queryRedisConfig() {
        return new HashMap<String, String>() {{
            put("host", host);
            put("port", String.valueOf(port));
            put("password", password);
        }};
    }

    @Override
    public void pushMessage(String gatewayId, Object message) {
        publisher.pushMessage(gatewayId, message);
    }
}
