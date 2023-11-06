package cn.cactusli.gateway.core.executor;

import cn.cactusli.gateway.core.datasource.Connection;
import cn.cactusli.gateway.core.session.Configuration;

/**
 * Package: cn.cactusli.gateway.executor
 * Description:
 *  简单执行器
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/14 10:39
 * @Github https://github.com/lixuanfengs
 */
public class SimpleExecutor extends BaseExecutor {

    public SimpleExecutor(Configuration configuration, Connection connection) {
        super(configuration, connection);
    }

    @Override
    protected Object doExec(String methodName, String[] parameterTypes, Object[] args) {
        /*
         * 调用服务
         * 封装参数 PS：为什么这样构建参数，可以参考测试案例；cn.cactusli.gateway.test.RPCTest
         * 01(允许)：java.lang.String
         * 02(允许)：cn.cactusli.gateway.rpc.dto.XReq
         * 03(拒绝)：java.lang.String, cn.bugstack.cactusli.rpc.dto.XReq —— 不提供多参数方法的处理
         * */
        return connection.execute(methodName, parameterTypes, new String[]{"ignore"}, args);
    }
}
