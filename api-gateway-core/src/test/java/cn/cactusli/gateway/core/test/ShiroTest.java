package cn.cactusli.gateway.core.test;

import cn.cactusli.gateway.core.authorization.IAuth;
import cn.cactusli.gateway.core.authorization.JwtUtil;
import cn.cactusli.gateway.core.authorization.auth.AuthService;
import io.jsonwebtoken.Claims;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Package: cn.cactusli.gateway.core.test
 * Description:
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/14 15:23
 * @Github https://github.com/lixuanfengs
 */
public class ShiroTest {

    private final static Logger logger = LoggerFactory.getLogger(ShiroTest.class);


    @Test
    public void test_auth_service() {
        IAuth auth = new AuthService();
        boolean validate = auth.validate("cactusli", "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJEUGlqOGlVWSIsImV4cCI6MTY4NzMzNjU1NSwiaWF0IjoxNjg2NzMxNzU1LCJrZXkiOiJEUGlqOGlVWSJ9.1QN8X6eMdKynSJL1QLXraqe4506Is1uzk8-o8PNk8QQ");
        System.out.println(validate ? "验证成功" : "验证失败");
    }


    @Test
    public void test_awt() {
        String issuer = "cactusli";
        long ttlMillis = 7 * 24 * 60 * 60 * 1000L;
        Map<String, Object> claims = new HashMap<>();
        claims.put("key", "cactusli");

        // 编码
        String token = JwtUtil.encode(issuer, ttlMillis, claims);
        System.out.println(token);

        // 解码
        Claims parser = JwtUtil.decode(token);
        System.out.println(parser.getSubject());
    }

    @Test
    public void test_shiro() {
        // 1. 获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:test-shiro.ini");

        // 2. 得到SecurityManager实例 并绑定给SecurityUtils
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        // 3. 得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();

        // 4. 默认提供的验证方式；UsernamePasswordToken
        UsernamePasswordToken token = new UsernamePasswordToken("cactusli", "1234");

        try {
            //5.1、登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            //5.2、身份验证失败
            System.out.println("身份验证失败");
        }

        System.out.println(subject.isAuthenticated() ? "验证成功" : "验证失败");

        // 6. 退出
        subject.logout();
    }
}
