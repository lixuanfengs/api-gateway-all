package cn.cactusli.gateway.rpc;

import cn.cactusli.gateway.rpc.dto.XReq;

/**
 * Package: cn.cactusli.gateway.rpc.dto
 * Description:
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/2 16:14
 * @Github https://github.com/lixuanfengs
 */
public interface IActivityBooth {

    String sayHello(String str);

    String insert(XReq req);

    String test(String str, XReq req);

}
