package cn.cactusli.gateway.center.application;

import java.util.Map;

/**
 * Package: cn.cactusli.gateway.center.application
 * Description:
 *  消息服务
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/9/14 15:11
 * @Github https://github.com/lixuanfengs
 */
public interface IMessageService {

    Map<String, String> queryRedisConfig();

    void pushMessage(String gatewayId, Object message);
}
