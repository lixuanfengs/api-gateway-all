package cn.cactusli.gateway.core.bind;

import cn.cactusli.gateway.core.executor.result.SessionResult;

import java.util.Map;

/**
 * Package: cn.cactusli.gateway.session.bind
 * Description:
 *  统一泛化调用接口
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/2 11:00
 * @Github https://github.com/lixuanfengs
 */
public interface IGenericReference {

    SessionResult $invoke(Map<String, Object> params);

}
