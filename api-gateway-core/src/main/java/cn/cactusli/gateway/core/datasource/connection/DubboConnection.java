package cn.cactusli.gateway.core.datasource.connection;

import cn.cactusli.gateway.core.datasource.Connection;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.config.utils.SimpleReferenceCache;
import org.apache.dubbo.rpc.service.GenericService;

/**
 * Package: cn.cactusli.gateway.datasource.unpooled
 * Description:
 *  RPC Dubbo Connection
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/7 15:16
 * @Github https://github.com/lixuanfengs
 */
public class DubboConnection implements Connection {

    private final GenericService genericService;

    public DubboConnection(ApplicationConfig applicationConfig, RegistryConfig registryConfig, ReferenceConfig<GenericService> reference) {
        // 连接远程服务
        DubboBootstrap bootstrap = DubboBootstrap.getInstance();
        bootstrap.application(applicationConfig).registry(registryConfig).reference(reference).start();
        // 获取泛化接口
        SimpleReferenceCache cache = SimpleReferenceCache.getCache();
        genericService = cache.get(reference);
    }

    /**
     * Dubbo 泛化调用：https://dubbo.apache.org/zh/docsv2.7/user/examples/generic-reference/
     */
    @Override
    public Object execute(String method, String[] parameterTypes, String[] parameterNames, Object[] args) {
        return genericService.$invoke(method, parameterTypes, args);
    }
}
