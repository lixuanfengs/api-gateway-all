package cn.cactusli.gateway.core.authorization.auth;

import cn.cactusli.gateway.core.authorization.GatewayAuthorizingToken;
import cn.cactusli.gateway.core.authorization.IAuth;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * Package: cn.cactusli.gateway.authorization.auth
 * Description:
 *  认证服务实现
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/14 16:18
 * @Github https://github.com/lixuanfengs
 */
public class AuthService implements IAuth {

    private Subject subject;

    public AuthService() {
        // 1. 获取 SecurityManager 工厂，此处使用 shiro.ini 配置文件初始化 SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        // 2. 得到 SecurityManager 实例 并绑定给 SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        // 3. 得到 Subject 及 Token
        this.subject = SecurityUtils.getSubject();
    }

    @Override
    public boolean validate(String id, String token) {
        try {
            // 身份验证
            subject.login(new GatewayAuthorizingToken(id, token));
            // 返回结果
            return subject.isAuthenticated();
        } finally {
            // 退出
            subject.logout();
        }
    }
}
