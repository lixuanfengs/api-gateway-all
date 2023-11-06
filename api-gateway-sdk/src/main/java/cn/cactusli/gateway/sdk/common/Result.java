package cn.cactusli.gateway.sdk.common;

/**
 * Package: cn.cactusli.gateway.sdk.common
 * Description:
 *  统一返回对象中 Code 码、Info 信息、Data 数据
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/9/5 17:13
 * @Github https://github.com/lixuanfengs
 */
public class Result<T> {

    private String code;
    private String info;
    
    private T data;

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }

    public T getData() {
        return data;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setData(T data) {
        this.data = data;
    }
}
