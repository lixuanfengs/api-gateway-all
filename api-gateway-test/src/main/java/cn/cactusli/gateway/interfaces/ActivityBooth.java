package cn.cactusli.gateway.interfaces;

import cn.cactusli.gateway.rpc.IActivityBooth;
import cn.cactusli.gateway.rpc.dto.XReq;
import cn.cactusli.gateway.sdk.annotaion.ApiProducerClazz;
import cn.cactusli.gateway.sdk.annotaion.ApiProducerMethod;
import com.alibaba.fastjson2.JSON;
import org.apache.dubbo.config.annotation.Service;

/**
 * Package: cn.cactusli.gateway.interfaces
 * Description:
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/2 16:46
 * @Github https://github.com/lixuanfengs
 */
// 前面章节不涉及到上报服务，需要把以下配置注释掉 ApiProducerClazz、ApiProducerMethod 去掉。
@Service(version = "1.0.0")
@ApiProducerClazz(interfaceName = "活动服务", interfaceVersion = "1.0.0")
public class ActivityBooth implements IActivityBooth {

    @Override
    @ApiProducerMethod(methodName = "探活方法", uri = "/cactus/activity/sayHello", httpCommandType = "GET", auth = 0)
    public String sayHello(String str) {
        return "hello " + str + " by api-gateway-test-provider";
    }

    @Override
    @ApiProducerMethod(methodName = "插入方法", uri = "/cactus/activity/insert", httpCommandType = "POST", auth = 1)
    public String insert(XReq req) {
        return "hello " + JSON.toJSONString(req) + " by api-gateway-test-provider";
    }

    @Override
    @ApiProducerMethod(methodName = "测试方法", uri = "/cactus/activity/test", httpCommandType = "POST", auth = 0)
    public String test(String str, XReq req) {
        return "hello " + str + JSON.toJSONString(req) + " by api-gateway-test-provider";
    }
}
