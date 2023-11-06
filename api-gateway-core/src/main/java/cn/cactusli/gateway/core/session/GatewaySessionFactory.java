package cn.cactusli.gateway.core.session;

/**
 * Package: cn.cactusli.gateway.session
 * Description:
 *  网关会话工厂接口
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/6 10:45
 * @Github https://github.com/lixuanfengs
 */
public interface GatewaySessionFactory {

    GatewaySession openSession(String uri);
}
