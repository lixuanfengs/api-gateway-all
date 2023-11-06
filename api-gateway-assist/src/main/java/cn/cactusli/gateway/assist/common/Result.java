package cn.cactusli.gateway.assist.common;

/**
 * Package: cn.cactusli.gateway.assist.common
 * Description:
 *  统一返回对象中，Code码、Info描述
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/28 10:24
 * @Github https://github.com/lixuanfengs
 */
public class Result<T> {

    private String code;
    private String info;
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
