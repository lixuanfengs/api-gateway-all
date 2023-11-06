package cn.cactusli.gateway.core.socket.agreement;

/**
 * Package: cn.cactusli.gateway.socket.agreement
 * Description:
 *  网关结果封装
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/19 10:23
 * @Github https://github.com/lixuanfengs
 */
public class GatewayResultMessage {

    private String node;
    private String code;
    private String info;
    private Object data;

    protected GatewayResultMessage(String code, String info, Object data) {
        this.code = code;
        this.info = info;
        this.data = data;
    }

    public static GatewayResultMessage buildSuccess(Object data) {
        return new GatewayResultMessage(AgreementConstants.ResponseCode._200.getCode(), AgreementConstants.ResponseCode._200.getInfo(), data);
    }

    public static GatewayResultMessage buildError(String code, String info) {
        return new GatewayResultMessage(code, info, null);
    }

    public String getNode() {
        return node;
    }

    public GatewayResultMessage setNode(String node) {
        this.node = node;
        return this;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }

    public Object getData() {
        return data;
    }


}
