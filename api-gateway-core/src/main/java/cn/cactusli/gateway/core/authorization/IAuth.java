package cn.cactusli.gateway.core.authorization;

/**
 * Package: cn.cactusli.gateway.authorization
 * Description:
 *  认证服务接口
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/14 16:16
 * @Github https://github.com/lixuanfengs
 */
public interface IAuth {
    boolean validate(String id, String token);
}
