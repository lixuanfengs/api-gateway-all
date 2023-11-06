package cn.cactusli.gateway.sdk.annotaion;

import java.lang.annotation.*;

/**
 * Package: cn.cactusli.gateway.sdk.annotaion
 * Description:
 *  网关API生产者自定义注解
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/8/29 16:27
 * @Github https://github.com/lixuanfengs
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ApiProducerClazz {

    /** 接口名称 */
    String interfaceName() default "";

    /** 接口版本 */
    String interfaceVersion() default "";
}
