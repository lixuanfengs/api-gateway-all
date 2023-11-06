package cn.cactusli.gateway.core.type;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Package: cn.cactusli.gateway.type
 * Description:
 *  基本类型注册器
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/13 10:18
 * @Github https://github.com/lixuanfengs
 */
public class SimpleTypeRegistry {

    private static final Set<String> SIMPLE_TYPE_SET = new HashSet<>();

    static {
        SIMPLE_TYPE_SET.add(String.class.getName());
        SIMPLE_TYPE_SET.add(Byte.class.getName());
        SIMPLE_TYPE_SET.add(Short.class.getName());
        SIMPLE_TYPE_SET.add(Character.class.getName());
        SIMPLE_TYPE_SET.add(Integer.class.getName());
        SIMPLE_TYPE_SET.add(Long.class.getName());
        SIMPLE_TYPE_SET.add(Float.class.getName());
        SIMPLE_TYPE_SET.add(Double.class.getName());
        SIMPLE_TYPE_SET.add(Boolean.class.getName());
        SIMPLE_TYPE_SET.add(Date.class.getName());
        SIMPLE_TYPE_SET.add(Class.class.getName());
        SIMPLE_TYPE_SET.add(BigInteger.class.getName());
        SIMPLE_TYPE_SET.add(BigDecimal.class.getName());
    }

    private SimpleTypeRegistry() {
        // Prevent Instantiation
    }

    /*
     * Tells us if the class passed in is a known common type
     *
     * @param clazz The class to check
     * @return True if the class is known
     */
    public static boolean isSimpleType(String clazz) {
        return SIMPLE_TYPE_SET.contains(clazz);
    }
}
