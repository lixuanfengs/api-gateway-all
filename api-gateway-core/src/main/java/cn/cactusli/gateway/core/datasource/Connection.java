package cn.cactusli.gateway.core.datasource;

/**
 * Package: cn.cactusli.gateway.datasource
 * Description:
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/7 15:06
 * @Github https://github.com/lixuanfengs
 */
public interface Connection {

    Object execute(String method, String[] parameterTypes, String[] parameterNames, Object[] args);

}
