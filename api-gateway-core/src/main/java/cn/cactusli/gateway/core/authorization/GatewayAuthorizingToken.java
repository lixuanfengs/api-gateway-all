package cn.cactusli.gateway.core.authorization;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * Package: cn.cactusli.gateway.authorization
 * Description:
 *  验证 Token
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/14 16:23
 * @Github https://github.com/lixuanfengs
 */
public class GatewayAuthorizingToken implements AuthenticationToken {


    private static final long serialVersionUID = 1L;

    // 通信管道ID
    private String channelId;

    // JSON WEB TOKEN
    private String jwt;

    public GatewayAuthorizingToken() {
    }

    public GatewayAuthorizingToken(String channelId, String jwt) {
        this.channelId = channelId;
        this.jwt = jwt;
    }

    @Override
    public Object getPrincipal() {
        return channelId;
    }

    @Override
    public Object getCredentials() {
        return this.jwt;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

}
