package cn.cactusli.gateway.sdk.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Package: cn.cactusli.gateway.sdk.config
 * Description:
 *      应用配置
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/8/29 16:39
 * @Github https://github.com/lixuanfengs
 */
@ConfigurationProperties(prefix = "gateway.sdk")
public class GatewaySDKServiceProperties {

    /** 网关注册中心地址 */
    private String address;
    /** 系统标识 */
    private String systemId;
    /** 系统名称 */
    private String systemName;
    /** RPC注册中心；zookeeper://127.0.0.1:2181*/
    private String systemRegistry;

    /** 程序是否启用 */
    private boolean enabled = true;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getSystemRegistry() {
        return systemRegistry;
    }

    public void setSystemRegistry(String systemRegistry) {
        this.systemRegistry = systemRegistry;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}
