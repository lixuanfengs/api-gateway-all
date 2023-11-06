package cn.cactusli.gateway.core.bind;

import cn.cactusli.gateway.core.session.GatewaySession;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Package: cn.cactusli.gateway.bind
 * Description:
 *  映射代理调用
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/6 10:46
 * @Github https://github.com/lixuanfengs
 */
public class MapperProxy implements MethodInterceptor {

    private GatewaySession gatewaySession;
    private final String uri;

    public MapperProxy(GatewaySession gatewaySession, String uri) {
        this.gatewaySession = gatewaySession;
        this.uri = uri;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        MapperMethod linkMethod = new MapperMethod(uri, method, gatewaySession.getConfiguration());
        return linkMethod.execute(gatewaySession, (Map<String, Object>) args[0]);
    }
}
