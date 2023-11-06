package cn.cactusli.gateway.sdk;

/**
 * Package: cn.cactusli.gateway.sdk
 * Description:
 *  网关异常类
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/9/5 17:11
 * @Github https://github.com/lixuanfengs
 */
public class GatewayException extends RuntimeException {

    public GatewayException(String message) {
        super(message);
    }

    public GatewayException(String message, Throwable cause) {
        super(message, cause);
    }
}
