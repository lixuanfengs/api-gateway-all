package cn.cactusli.gateway.sdk.annotaion;

import java.lang.annotation.*;

/**
 * Package: cn.cactusli.gateway.sdk.annotaion
 * Description:
 *   网关API生产者自定义注解
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/8/29 16:32
 * @Github https://github.com/lixuanfengs
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ApiProducerMethod {

    /** 方法名称 */
    String methodName() default "";

    /** 访问路径；/cactus/activity/sayHi */
    String uri() default "";

    /** 接口类型；GET、POST、PUT、DELETE */
    String httpCommandType() default "GET";

    /** 是否认证；true = 1是、false = 0否 */
    int auth() default 0;

}
