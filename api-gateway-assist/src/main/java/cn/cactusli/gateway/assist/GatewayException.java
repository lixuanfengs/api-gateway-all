package cn.cactusli.gateway.assist;

/**
 * Package: cn.cactusli.gateway.assist
 * Description:
 *  网关异常
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/8/4 18:00
 * @Github https://github.com/lixuanfengs
 */
public class GatewayException extends RuntimeException {

    public GatewayException(String message) {
        super(message);
    }

    public GatewayException(String message, Throwable cause) {
        super(message, cause);
    }

    public GatewayException(Throwable cause) {
        super(cause);
    }
}
