package cn.cactusli.gateway.center.domain.message.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Package: cn.cactusli.gateway.center.domain.message.config
 * Description:
 *      Redis 消息监听推送配置
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/9/14 14:56
 * @Github https://github.com/lixuanfengs
 */
@Configuration
public class PublisherConfig {

    @Bean
    public RedisTemplate<String, Object> redisMessageTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        redisTemplate.setConnectionFactory(connectionFactory);

        // 使用String序列化器
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        // 使用GenericJackson2JsonRedisSerializer作为值的序列化器
        RedisSerializer<Object> jsonSerializer = new GenericJackson2JsonRedisSerializer();
        redisTemplate.setValueSerializer(jsonSerializer);

        // 设置Hash Key和Value序列化模式
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(jsonSerializer);

        // 启用事务支持
        redisTemplate.setEnableTransactionSupport(true);

        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }
}
