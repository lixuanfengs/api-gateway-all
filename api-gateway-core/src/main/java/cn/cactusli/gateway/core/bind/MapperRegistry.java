package cn.cactusli.gateway.core.bind;

import cn.cactusli.gateway.core.mapping.HttpStatement;
import cn.cactusli.gateway.core.session.Configuration;
import cn.cactusli.gateway.core.session.GatewaySession;

import java.util.HashMap;
import java.util.Map;

/**
 * Package: cn.cactusli.gateway.bind
 * Description:
 *  泛化调用注册器
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/6 11:01
 * @Github https://github.com/lixuanfengs
 */
public class MapperRegistry {

    private final Configuration configuration;

    public MapperRegistry(Configuration configuration) {
        this.configuration = configuration;
    }

    // 泛化调用静态代理工厂
    private final Map<String, MapperProxyFactory> knownMappers = new HashMap<>();

    public IGenericReference getMapper(String uri, GatewaySession gatewaySession) {
        final MapperProxyFactory mapperProxyFactory = knownMappers.get(uri);
        if (mapperProxyFactory == null) {
            throw new RuntimeException("Uri " + uri + " is not known to the MapperRegistry.");
        }
        try {
            return mapperProxyFactory.newInstance(gatewaySession);
        } catch (Exception e) {
            throw new RuntimeException("Error getting mapper instance. Cause: " + e, e);
        }
    }

    public void addMapper(HttpStatement httpStatement) {
        String uri = httpStatement.getUri();
        // 如果重复注册则报错
        if (hasMapper(uri)) {
            throw new RuntimeException("Uri " + uri + " is already known to the MapperRegistry.");
        }
        knownMappers.put(uri, new MapperProxyFactory(uri));
        // 保存接口映射信息
        configuration.addHttpStatement(httpStatement);
    }

    public <T> boolean hasMapper(String uri) {
        return knownMappers.containsKey(uri);
    }

}
