package cn.cactusli.gateway.sdk.application;

import cn.cactusli.gateway.sdk.GatewayException;
import cn.cactusli.gateway.sdk.annotaion.ApiProducerClazz;
import cn.cactusli.gateway.sdk.annotaion.ApiProducerMethod;
import cn.cactusli.gateway.sdk.config.GatewaySDKServiceProperties;
import cn.cactusli.gateway.sdk.domain.service.GatewayCenterService;
import com.alibaba.fastjson2.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Method;


/**
 * Package: cn.cactusli.gateway.sdk.application
 * Description:
 *  应用服务注册
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/8/29 16:42
 * @Github https://github.com/lixuanfengs
 */
public class GatewaySDKApplication implements BeanPostProcessor {

    private Logger logger = LoggerFactory.getLogger(GatewaySDKApplication.class);

    private GatewaySDKServiceProperties properties;
    private GatewayCenterService gatewayCenterService;

    public GatewaySDKApplication(GatewaySDKServiceProperties properties, GatewayCenterService gatewayCenterService) {
        this.properties = properties;
        this.gatewayCenterService = gatewayCenterService;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        ApiProducerClazz apiProducerClazz = bean.getClass().getAnnotation(ApiProducerClazz.class);
        if (null == apiProducerClazz) return bean;
        // 1. 系统信息
        logger.info("\n应用注册：系统信息 \nsystemId: {} \nsystemName: {} \nsystemType: {} \nsystemRegistry: {}", properties.getSystemId(), properties.getSystemName(), "RPC", properties.getSystemRegistry());
        gatewayCenterService.doRegisterApplication(properties.getAddress(), properties.getSystemId(), properties.getSystemName(), "RPC", properties.getSystemRegistry());
        // 2. 接口信息
        Class<?>[] interfaces = bean.getClass().getInterfaces();
        if (interfaces.length !=1) {
            throw new GatewayException(bean.getClass().getName() + "interfaces not one this is" + JSON.toJSONString(interfaces));
        }
        String interfaceId = interfaces[0].getName();
        logger.info("\n应用注册：接口信息 \nsystemId: {} \ninterfaceId: {} \ninterfaceName: {} \ninterfaceVersion: {}", properties.getSystemId(), interfaceId, apiProducerClazz.interfaceName(), apiProducerClazz.interfaceVersion());
        gatewayCenterService.doRegisterApplicationInterface(properties.getAddress(),
                properties.getSystemId(),
                interfaceId,
                apiProducerClazz.interfaceName(),
                apiProducerClazz.interfaceVersion());

        // 3. 方法信息
        Method[] methods = bean.getClass().getMethods();
        for (Method method : methods) {
            ApiProducerMethod apiProducerMethod = method.getAnnotation(ApiProducerMethod.class);
            if (null == apiProducerMethod) continue;
            // 3.1 解析参数
            Class<?>[] parameterTypes = method.getParameterTypes();
            StringBuilder parameters = new StringBuilder();
            for (Class<?> parameterType : parameterTypes) {
                parameters.append(parameterType.getName()).append(",");
            }
            String parameterType = parameters.toString().substring(0, parameters.toString().lastIndexOf(","));
            logger.info("\n应用注册：方法信息 \nsystemId: {} \ninterfaceId: {} \nmethodId: {} \nmethodName: {} \nparameterType: {} \nuri: {} \nhttpCommandType: {} \nauth: {}",
                    properties.getSystemId(),
                    interfaceId,
                    method.getName(),
                    apiProducerMethod.methodName(),
                    parameterType,
                    apiProducerMethod.uri(),
                    apiProducerMethod.httpCommandType(),
                    apiProducerMethod.auth());
            gatewayCenterService.doRegisterApplicationInterfaceMethod(properties.getAddress(),
                    properties.getSystemId(),
                    interfaceId,
                    method.getName(),
                    apiProducerMethod.methodName(),
                    parameterType,
                    apiProducerMethod.uri(),
                    apiProducerMethod.httpCommandType(),
                    apiProducerMethod.auth());
        }

        // 注册完成，执行事件通知
        gatewayCenterService.doRegisterEvent(properties.getAddress(), properties.getSystemId());

        return bean;
    }
}
