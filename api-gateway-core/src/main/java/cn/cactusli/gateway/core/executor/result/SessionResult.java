package cn.cactusli.gateway.core.executor.result;

/**
 * Package: cn.cactusli.gateway.executor.result
 * Description:
 *  网关结果封装
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/13 16:04
 * @Github https://github.com/lixuanfengs
 */
public class SessionResult {
    private String code;
    private String info;
    private Object data;

    public SessionResult(String code, String info, Object data) {
        this.code = code;
        this.info = info;
        this.data = data;
    }
    public static SessionResult buildSuccess(Object data){
        return new SessionResult("0000","调用成功", data);
    }

    public static SessionResult buildError(Object data){
        return new SessionResult("0001","调用失败", data);
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
