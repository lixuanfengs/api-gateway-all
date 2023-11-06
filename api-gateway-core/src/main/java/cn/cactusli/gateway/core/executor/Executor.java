package cn.cactusli.gateway.core.executor;

import cn.cactusli.gateway.core.executor.result.SessionResult;
import cn.cactusli.gateway.core.mapping.HttpStatement;

import java.util.Map;

/**
 * Package: cn.cactusli.gateway.executor
 * Description:
 *  执行器
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/13 16:03
 * @Github https://github.com/lixuanfengs
 */
public interface Executor {

    SessionResult exec(HttpStatement httpStatement, Map<String, Object> params) throws Exception;

}
