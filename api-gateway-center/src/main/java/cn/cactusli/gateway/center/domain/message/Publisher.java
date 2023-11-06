package cn.cactusli.gateway.center.domain.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Package: cn.cactusli.gateway.center.domain.message
 * Description:
 *  消息推送
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/9/14 14:55
 * @Github https://github.com/lixuanfengs
 */
@Service
public class Publisher {

    private final RedisTemplate<String, Object> redisMessageTemplate;

    @Autowired
    public Publisher(RedisTemplate<String, Object> redisMessageTemplate) {
        this.redisMessageTemplate = redisMessageTemplate;
    }

    public void pushMessage(String topic, Object message) {
        redisMessageTemplate.convertAndSend(topic, message);
    }

}
