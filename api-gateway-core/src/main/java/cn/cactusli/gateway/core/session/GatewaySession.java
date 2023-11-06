package cn.cactusli.gateway.core.session;

import cn.cactusli.gateway.core.bind.IGenericReference;

import java.util.Map;

/**
 * Package: cn.cactusli.gateway.session
 * Description:
 *  用户处理网关 HTTP 请求
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/6 10:44
 * @Github https://github.com/lixuanfengs
 */
public interface GatewaySession {

    Object get(String uri, Map<String, Object> parameter);

    Object post(String methodName, Map<String, Object> params);

    IGenericReference getMapper();

    Configuration getConfiguration();

}
