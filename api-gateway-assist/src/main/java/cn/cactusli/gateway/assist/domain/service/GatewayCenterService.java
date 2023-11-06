package cn.cactusli.gateway.assist.domain.service;

import cn.cactusli.gateway.assist.GatewayException;
import cn.cactusli.gateway.assist.common.Result;
import cn.cactusli.gateway.assist.domain.model.aggregates.ApplicationSystemRichInfo;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Package: cn.cactusli.gateway.assist.domain.service
 * Description:
 *  网关注册服务；如果说网关中心还提供了MQ、Socket的通信方式，那么这里就需要做一些策略实现处理
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/8/7 11:05
 * @Github https://github.com/lixuanfengs
 */
public class GatewayCenterService {

    private Logger logger = LoggerFactory.getLogger(GatewayCenterService.class);

    public void doRegister(String address, String groupId, String gatewayId, String gatewayName, String gatewayAddress) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("groupId", groupId);
        paramMap.put("gatewayId", gatewayId);
        paramMap.put("gatewayName", gatewayName);
        paramMap.put("gatewayAddress", gatewayAddress);
        String resultStr = null;
        try {
            resultStr = HttpUtil.post(address + "/cactus/admin/config/registerGateway", paramMap, 5000);
        } catch (Exception e) {
            logger.error("网关服务注册异常，链接资源不可用：{}", address + "/cactus/admin/config/registerGateway");
            throw e;
        }
        Result<Boolean> result = JSON.parseObject(resultStr, new TypeReference<Result<Boolean>>() {
        });
        logger.info("向网关中心注册网关算力服务 gatewayId：{} gatewayName：{} gatewayAddress：{} 注册结果：{}", gatewayId, gatewayName, gatewayAddress, resultStr);
        if (!"0000".equals(result.getCode()))
            throw new GatewayException("网关服务注册异常 [gatewayId：" + gatewayId + "] 、[gatewayAddress：" + gatewayAddress + "]");
    }

    public ApplicationSystemRichInfo pullApplicationSystemRichInfo(String address, String gatewayId, String systemId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("gatewayId", gatewayId);
        paramMap.put("systemId", systemId);

        String resultStr = null;
        try {
            resultStr = HttpUtil.post(address + "/cactus/admin/config/queryApplicationSystemRichInfo", paramMap, 5000);
        } catch (Exception e) {
            logger.error("网关服务拉取异常，链接资源不可用：{}", address + "/cactus/admin/config/queryApplicationSystemRichInfo");
            throw  e;
        }
        Result<ApplicationSystemRichInfo> result = JSON.parseObject(resultStr, new TypeReference<Result<ApplicationSystemRichInfo>>(){});
        logger.info("从网关中心拉取应用服务和接口的配置信息到本地完成注册。gatewayId：{}", gatewayId);
        if (!"0000".equals(result.getCode()))
            throw new GatewayException("从网关中心拉取应用服务和接口的配置信息到本地完成注册异常 [gatewayId：" + gatewayId + "]");
        return result.getData();
    }


    public Map<String, String> queryRedisConfig(String address) {
        Map<String, Object> paramMap = new HashMap<>();


        String resultStr;
        try {
            resultStr = HttpUtil.post(address + "/cactus/admin/config/queryRedisConfig", paramMap, 5000);
        } catch (Exception e) {
            logger.error("网关服务拉取配置异常，链接资源不可用：{}", address + "/cactus/admin/config/queryRedisConfig", e);
            throw e;
        }
        Result<Map<String, String>> result = JSON.parseObject(resultStr, new TypeReference<Result<Map<String, String>>>() {
        });
        logger.info("从网关中心拉取Redis配置信息完成。result：{}", resultStr);
        if (!"0000".equals(result.getCode()))
            throw new GatewayException("从网关中心拉取Redis配置信息异常");
        return result.getData();
    }


}
