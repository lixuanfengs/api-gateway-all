package cn.cactusli.gateway.core.session;

import cn.cactusli.gateway.core.authorization.IAuth;
import cn.cactusli.gateway.core.authorization.auth.AuthService;
import cn.cactusli.gateway.core.bind.IGenericReference;
import cn.cactusli.gateway.core.bind.MapperRegistry;
import cn.cactusli.gateway.core.datasource.Connection;
import cn.cactusli.gateway.core.executor.Executor;
import cn.cactusli.gateway.core.executor.SimpleExecutor;
import cn.cactusli.gateway.core.mapping.HttpStatement;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;

import java.util.HashMap;
import java.util.Map;

/**
 * Package: cn.cactusli.gateway.session
 * Description:
 *  会话生命周期配置项
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/2 11:13
 * @Github https://github.com/lixuanfengs
 */
public class Configuration {

    // 网关 Netty 服务地址
    private String hostName = "127.0.0.1";
    // 网关 Netty 服务端口
    private int port = 7397;
    // 网关 Netty 服务线程数配置
    private int bossNThreads = 1;
    private int workNThreads = 4;

    private final MapperRegistry mapperRegistry = new MapperRegistry(this);

    private final Map<String, HttpStatement> httpStatements = new HashMap<>();

    private final IAuth auth = new AuthService();

    // RPC 应用服务配置项 api-gateway-test
    private final Map<String, ApplicationConfig> applicationConfigMap = new HashMap<>();
    // RPC 注册中心配置项 zookeeper://127.0.0.1:2181
    private final Map<String, RegistryConfig> registryConfigMap = new HashMap<>();
    // RPC 泛化服务配置项 cn.bugstack.gateway.rpc.IActivityBooth
    private final Map<String, ReferenceConfig<GenericService>> referenceConfigMap = new HashMap<>();


    public Configuration() {
//        // TODO 后期从配置中获取
//        ApplicationConfig application = new ApplicationConfig();
//        application.setName("api-gateway-test");
//        application.setQosEnable(false);
//
//        RegistryConfig registry = new RegistryConfig();
//        registry.setAddress("zookeeper://192.168.1.107:2181");
//        registry.setRegister(false);
//
//        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
//        reference.setInterface("cn.cactusli.gateway.rpc.IActivityBooth");
//        reference.setVersion("1.0.0");
//        reference.setGeneric("true");
//
//        applicationConfigMap.put("api-gateway-test", application);
//        registryConfigMap.put("api-gateway-test", registry);
//        referenceConfigMap.put("cn.cactusli.gateway.rpc.IActivityBooth", reference);
    }

    public synchronized void registryConfig(String applicationName, String address, String interfaceName, String version) {
        if (applicationConfigMap.get(applicationName) == null) {
            ApplicationConfig application = new ApplicationConfig();
            application.setName(applicationName);
            application.setQosEnable(false);
            applicationConfigMap.put(applicationName, application);
        }

        if (registryConfigMap.get(applicationName) == null) {
            RegistryConfig registry = new RegistryConfig();
            registry.setAddress(address);
            registry.setRegister(false);
            registryConfigMap.put(applicationName, registry);
        }

        if (referenceConfigMap.get(interfaceName) == null) {
            ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
            reference.setInterface(interfaceName);
            reference.setVersion(version);
            reference.setGeneric("true");
            referenceConfigMap.put(interfaceName, reference);
        }
    }

    public ApplicationConfig getApplicationConfig(String applicationName) {
        return applicationConfigMap.get(applicationName);
    }

    public RegistryConfig getRegistryConfig(String applicationName) {
        return registryConfigMap.get(applicationName);
    }

    public ReferenceConfig<GenericService> getReferenceConfig(String interfaceName) {
        return referenceConfigMap.get(interfaceName);
    }

    public void addMapper(HttpStatement httpStatement) {
        mapperRegistry.addMapper(httpStatement);
    }

    public IGenericReference getMapper(String uri, GatewaySession gatewaySession) {
        return mapperRegistry.getMapper(uri, gatewaySession);
    }

    public void addHttpStatement(HttpStatement httpStatement) {
        httpStatements.put(httpStatement.getUri(), httpStatement);
    }

    public HttpStatement getHttpStatement(String uri) {
        return httpStatements.get(uri);
    }

    public Executor newExecutor(Connection connection) {
        return new SimpleExecutor(this, connection);
    }

    public boolean authValidate(String uId, String token) {
        return auth.validate(uId, token);
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getBossNThreads() {
        return bossNThreads;
    }

    public void setBossNThreads(int bossNThreads) {
        this.bossNThreads = bossNThreads;
    }

    public int getWorkNThreads() {
        return workNThreads;
    }

    public void setWorkNThreads(int workNThreads) {
        this.workNThreads = workNThreads;
    }
}
