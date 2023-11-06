package cn.cactusli.gateway.core.test;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.config.utils.SimpleReferenceCache;
import org.apache.dubbo.rpc.service.GenericService;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Package: cn.cactusli.gateway.test
 * Description:
 * 泛化调用测试
 * 官网案例：https://dubbo.apache.org/zh/docs/advanced/generic-reference/
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/13 10:32
 * @Github https://github.com/lixuanfengs
 */
public class RPCTest {

    @Test
    public void test_rpc() {

        ApplicationConfig application = new ApplicationConfig();
        application.setName("api-gateway-test");
        application.setQosEnable(false);

        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("zookeeper://127.0.0.1:2181");
        registry.setRegister(false);

        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        reference.setInterface("cn.cactusli.gateway.rpc.IActivityBooth");
        reference.setVersion("1.0.0");
        reference.setGeneric("true");

        DubboBootstrap bootstrap = DubboBootstrap.getInstance();
        bootstrap.application(application)
                .registry(registry)
                .reference(reference)
                .start();

        SimpleReferenceCache cache = SimpleReferenceCache.getCache();
        GenericService genericService = cache.get(reference);

//        Object result = genericService.$invoke("sayHi", new String[]{"java.lang.String"}, new Object[]{"world"});
//        Map<String, Object> allRequestParams = new HashMap();
//        allRequestParams.put("name", "小傅哥");
//        allRequestParams.put("uid", "10001");
//        Object result = genericService.$invoke("insert", new String[]{"java.lang.Object"}, new Object[]{allRequestParams});

        String[] parameterTypes = new String[]{"java.lang.String", "cn.cactusli.gateway.rpc.dto.XReq"};

        Map<String, Object> params01 = new HashMap<>();
//        params.put("class", "cn.bugstack.gateway.rpc.dto.XReq");
        params01.put("str", "10008");

        Map<String, Object> params02 = new HashMap<>();
//        params.put("str", "10008");
        params02.put("uid", "10008");
        params02.put("name", "仙人球");

//        Object user = genericService.$invoke("sayHi", new String[]{"java.lang.String"}, params.values().toArray());
//        Object user = genericService.$invoke("insert", new String[]{"cn.bugstack.gateway.rpc.dto.XReq"}, new Object[]{params});
        Object user = genericService.$invoke("test", new String[]{"java.lang.String", "cn.cactusli.gateway.rpc.dto.XReq"}, new Object[]{params01.values().toArray()[0], params02});


        System.out.println(user);
    }

}
