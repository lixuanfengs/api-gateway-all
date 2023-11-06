package cn.cactusli.gateway.center.domain.manage.model.vo;

import java.util.List;

/**
 * Package: cn.cactusli.gateway.center.domain.manage.model.vo
 * Description:
 *   应用接口 VO
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/7/28 14:52
 * @Github https://github.com/lixuanfengs
 */
public class ApplicationInterfaceVO {

    /** 系统标识 */
    private String systemId;
    /** 接口标识 */
    private String interfaceId;
    /** 接口名称 */
    private String interfaceName;
    /** 接口版本 */
    private String interfaceVersion;
    /** 方法接口 */
    private List<ApplicationInterfaceMethodVO> methodList;

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getInterfaceVersion() {
        return interfaceVersion;
    }

    public void setInterfaceVersion(String interfaceVersion) {
        this.interfaceVersion = interfaceVersion;
    }

    public List<ApplicationInterfaceMethodVO> getMethodList() {
        return methodList;
    }

    public void setMethodList(List<ApplicationInterfaceMethodVO> methodList) {
        this.methodList = methodList;
    }

}
