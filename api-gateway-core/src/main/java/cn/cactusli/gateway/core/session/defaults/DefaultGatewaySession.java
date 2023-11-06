package cn.cactusli.gateway.core.session.defaults;

import cn.cactusli.gateway.core.bind.IGenericReference;
import cn.cactusli.gateway.core.executor.Executor;
import cn.cactusli.gateway.core.mapping.HttpStatement;
import cn.cactusli.gateway.core.session.GatewaySession;
import cn.cactusli.gateway.core.session.Configuration;

import java.util.Map;

/**
 * Package: cn.cactusli.gateway.session.defaults
 * Description:
 *  默认网关会话实现类
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/6 10:58
 * @Github https://github.com/lixuanfengs
 */
public class DefaultGatewaySession implements GatewaySession {

    private Configuration configuration;
    private String uri;
    private Executor executor;

    public DefaultGatewaySession(Configuration configuration, String uri, Executor executor) {
        this.configuration = configuration;
        this.uri = uri;
        this.executor = executor;
    }

    @Override
    public Object get(String methodName, Map<String, Object> parameter) {
        HttpStatement httpStatement = configuration.getHttpStatement(uri);
        try {
            return executor.exec(httpStatement, parameter);
        } catch (Exception e) {
            throw new RuntimeException("Error exec get. Cause：" + e);
        }
    }

    @Override
    public Object post(String methodName, Map<String, Object> params) {
        return get(methodName, params);
    }

    @Override
    public IGenericReference getMapper() {
        return configuration.getMapper(uri, this);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }
}
