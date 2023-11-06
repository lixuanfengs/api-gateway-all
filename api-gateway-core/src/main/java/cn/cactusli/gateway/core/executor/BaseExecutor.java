package cn.cactusli.gateway.core.executor;

import cn.cactusli.gateway.core.datasource.Connection;
import cn.cactusli.gateway.core.executor.result.SessionResult;
import cn.cactusli.gateway.core.mapping.HttpStatement;
import cn.cactusli.gateway.core.session.Configuration;
import cn.cactusli.gateway.core.type.SimpleTypeRegistry;
import com.alibaba.fastjson2.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Package: cn.cactusli.gateway.executor
 * Description:
 *  执行器抽象基类
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/14 9:40
 * @Github https://github.com/lixuanfengs
 */
public abstract class BaseExecutor implements Executor {

    private Logger logger = LoggerFactory.getLogger(BaseExecutor.class);

    protected Configuration configuration;
    protected Connection connection;

    public BaseExecutor(Configuration configuration, Connection connection) {
        this.configuration = configuration;
        this.connection = connection;
    }

    @Override
    public SessionResult exec(HttpStatement httpStatement, Map<String, Object> params) throws Exception {
        // 参数处理：后续需的一些参数校验也可以在这里封装
        String methodName = httpStatement.getMethodName();
        String parameterType = httpStatement.getParameterType();
        String[] parameterTypes = new String[]{parameterType};
        Object[] args = SimpleTypeRegistry.isSimpleType(parameterType) ? params.values().toArray() : new Object[]{params};
        logger.info("执行调用 method：{}#{}.{}({}) args：{}", httpStatement.getApplication(), httpStatement.getInterfaceName(), httpStatement.getMethodName(), JSON.toJSONString(parameterTypes), JSON.toJSONString(args));
        // 抽象方法
        try {
            Object data = doExec(methodName, parameterTypes, args);
            return SessionResult.buildSuccess(data);
        } catch (Exception e) {
            return SessionResult.buildError(e.getMessage());
        }
    }

    protected abstract Object doExec(String methodName, String[] parameterTypes, Object[] args);
}
