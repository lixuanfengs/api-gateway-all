package cn.cactusli.gateway.center.domain.docker.model.vo;

/**
 * Package: cn.cactusli.gateway.center.domain.docker.model.vo
 * Description:
 *  反向代理
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/10/7 11:02
 * @Github https://github.com/lixuanfengs
 */
public class LocationVO {

    /** 名称 */
    private String name;
    /** 指向地址 */
    private String proxy_pass;

    public LocationVO(String name, String proxy_pass) {
        this.name = name;
        this.proxy_pass = proxy_pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProxy_pass() {
        return proxy_pass;
    }

    public void setProxy_pass(String proxy_pass) {
        this.proxy_pass = proxy_pass;
    }

}
