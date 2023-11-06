package cn.cactusli.gateway.core.bind;

import cn.cactusli.gateway.core.mapping.HttpCommandType;
import cn.cactusli.gateway.core.session.Configuration;
import cn.cactusli.gateway.core.session.GatewaySession;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Package: cn.cactusli.gateway.bind
 * Description:
 *  绑定调用方法
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/6 10:41
 * @Github https://github.com/lixuanfengs
 */
public class MapperMethod {

    private String methodName;
    private final HttpCommandType command;

    public MapperMethod(String uri, Method method, Configuration configuration) {
        this.methodName = configuration.getHttpStatement(uri).getMethodName();
        this.command = configuration.getHttpStatement(uri).getHttpCommandType();
    }

    public Object execute(GatewaySession session, Map<String, Object> args) {
        Object result = null;
        switch (command) {
            case GET:
                result = session.get(methodName, args);
                break;
            case POST:
                result = session.post(methodName, args);
                break;
            case PUT:
                break;
            case DELETE:
                break;
            default:
                throw new RuntimeException("Unknown execution method for: " + command);
        }
        return result;
    }

}
