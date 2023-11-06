package cn.cactusli.gateway.center.domain.operation.model.vo;

/**
 * Package: cn.cactusli.gateway.center.domain.operation.model.vo
 * Description:
 *  应用服务 VO
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/9/27 11:04
 * @Github https://github.com/lixuanfengs
 */
public class ApplicationSystemDataVO {

    /** 系统标识 */
    private String systemId;
    /** 系统名称 */
    private String systemName;
    /** 系统类型；RPC、HTTP*/
    private String systemType;
    /** 注册中心；zookeeper://127.0.0.1:2181*/
    private String systemRegistry;

    public ApplicationSystemDataVO() {
    }

    public ApplicationSystemDataVO(String systemId, String systemName) {
        this.systemId = systemId;
        this.systemName = systemName;
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

    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    public String getSystemRegistry() {
        return systemRegistry;
    }

    public void setSystemRegistry(String systemRegistry) {
        this.systemRegistry = systemRegistry;
    }

}
