package cn.cactusli.gateway.center.infrastructure.common;

import com.alibaba.fastjson2.JSON;

import java.io.Serializable;

/**
 * Package: cn.cactusli.gateway.center.infrastructure.common
 * Description:
 *  统一返回对象中，Code码、Info描述
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/25 11:25
 * @Github https://github.com/lixuanfengs
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -3826891916021780628L;
    private String code;
    private String info;
    private T data;

    public Result(String code, String info, T data) {
        this.code = code;
        this.info = info;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
