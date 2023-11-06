package cn.cactusli.gateway.center.interfaces;

import cn.cactusli.gateway.center.application.IConfigManageService;
import cn.cactusli.gateway.center.application.ILoadBalancingService;
import cn.cactusli.gateway.center.application.IMessageService;
import cn.cactusli.gateway.center.domain.docker.model.aggregates.NginxConfig;
import cn.cactusli.gateway.center.domain.docker.model.vo.LocationVO;
import cn.cactusli.gateway.center.domain.docker.model.vo.UpstreamVO;
import cn.cactusli.gateway.center.domain.manage.model.aggregates.ApplicationSystemRichInfo;
import cn.cactusli.gateway.center.domain.manage.model.vo.*;
import cn.cactusli.gateway.center.infrastructure.common.ResponseCode;
import cn.cactusli.gateway.center.infrastructure.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Package: cn.cactusli.gateway.center.interfaces
 * Description:
 *  网关配置管理；服务分组、网关注册、服务关联
 *  1. 查询网关服务配置项信息：/cactus/admin/config/queryServerConfig
 *  2. 注册网关服务节点：/cactus/admin/config/registerGateway
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/25 11:08
 * @Github https://github.com/lixuanfengs
 */
@RestController
@RequestMapping("/cactus/admin/config")
public class GatewayConfigManage {

    private Logger logger = LoggerFactory.getLogger(GatewayConfigManage.class);
    @Resource
    private IConfigManageService configManageService;
    @Resource
    private IMessageService messageService;

    @Resource
    private ILoadBalancingService loadBalancingService;

    @GetMapping(value = "queryServerConfig", produces = "application/json;charset=utf-8")
    public Result<List<GatewayServerVO>> queryServerConfig() {
        try {
            logger.info("查询网关服务配置项信息");
            List<GatewayServerVO> gatewayServerVOList = configManageService.queryGatewayServerList();
            return new Result<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), gatewayServerVOList);
        } catch (Exception e) {
            logger.error("查询网关服务配置项信息异常", e);
            return new Result<>(ResponseCode.UN_ERROR.getCode(), e.getMessage(), null);
        }
    }

    @GetMapping(value = "queryServerDetailConfig", produces = "application/json;charset=utf-8")
    public Result<List<GatewayServerDetailVO>> queryServerDetailConfig(){
        try {
            logger.info("查询网关算力节点配置项信息");
            List<GatewayServerDetailVO> gatewayServerVOList = configManageService.queryGatewayServerDetailList();
            return new Result<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), gatewayServerVOList);
        } catch (Exception e) {
            logger.error("查询网关算力节点配置项信息异常", e);
            return new Result<>(ResponseCode.UN_ERROR.getCode(), e.getMessage(), null);
        }
    }

    @GetMapping(value = "queryGatewayDistributionList", produces = "application/json;charset=utf-8")
    public Result<List<GatewayDistributionVO>> queryGatewayDistributionList(){
        try {
            logger.info("查询网关分配配置项信息");
            List<GatewayDistributionVO> gatewayServerVOList = configManageService.queryGatewayDistributionList();
            return new Result<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), gatewayServerVOList);
        } catch (Exception e) {
            logger.error("查询网关分配配置项信息异常", e);
            return new Result<>(ResponseCode.UN_ERROR.getCode(), e.getMessage(), null);
        }
    }

    /**
     * 注册网关服务节点
     *
     * @param groupId        分组标识
     * @param gatewayId      网关标识
     * @param gatewayName    网关名称
     * @param gatewayAddress 网关地址
     * @return 注册状态
     */
    @PostMapping(value = "registerGateway")
    public Result<Boolean> registerGatewayServerNode(@RequestParam String groupId, @RequestParam String gatewayId, @RequestParam String gatewayName, @RequestParam String gatewayAddress) {
        try {
            logger.info("注册网关服务节点 gatewayId：{} gatewayName：{} gatewayAddress：{}", gatewayId, gatewayName, gatewayAddress);
            // 1. 注册&更新网关算力信息
            boolean done = configManageService.registerGatewayServerNode(groupId, gatewayId, gatewayName, gatewayAddress);
            // 2. 读取最新网关算力数据【由于可能来自多套注册中心，所以从数据库或者Redis中获取，更为准确】
            List<GatewayServerDetailVO> gatewayServerDetailVOS = configManageService.queryGatewayServerDetailList();
            // 3. 组装 Nginx 网关刷新配置
            Map<String, List<GatewayServerDetailVO>> gatewayServerDetailMap = gatewayServerDetailVOS.stream()
                    .collect(Collectors.groupingBy(GatewayServerDetailVO::getGroupId));
            Set<String> uniqueGroupIdList = gatewayServerDetailMap.keySet();
            // 3.1  Location 信息
            List<LocationVO> locationList = new ArrayList<>();
            for (String name : uniqueGroupIdList) {
                // location /api01/ {
                //     rewrite ^/api01/(.*)$ /$1 break;
                // 	   proxy_pass http://api01;
                // }
                locationList.add(new LocationVO("/" + name + "/", "http://" + name + "/;"));
            }
            // 3.2 Upstream 信息
            List<UpstreamVO> upstreamList = new ArrayList<>();
            for (String name : uniqueGroupIdList) {
                // upstream api01 {
                //     least_conn;
                //     server 192.168.1.216:9001;
                //     #server 192.168.1.216:9002;
                // }
                List<String> servers = gatewayServerDetailMap.get(name).stream()
                        .map(GatewayServerDetailVO::getGatewayAddress)
                        .collect(Collectors.toList());
                upstreamList.add(new UpstreamVO(name, "least_conn;", servers));
            }
            // 4. 刷新Nginx配置
            loadBalancingService.updateNginxConfig(new NginxConfig(upstreamList, locationList));
            return new Result<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), done);
        } catch (Exception e) {
            logger.error("注册网关服务节点异常", e);
            return new Result<>(ResponseCode.UN_ERROR.getCode(), e.getMessage(), false);
        }
    }

    /**
     * 网关算力与系统挂载配置
     * groupId --1vn--> gatewayId --1vn--> systemId
     * 10001 -> api-gateway-g3 -> api-gateway-test-01-provider
     * 10001 -> api-gateway-g3 -> api-gateway-test-02-provider
     * 10001 -> api-gateway-g4 -> api-gateway-test-03-provider
     * 10001 -> api-gateway-g4 -> api-gateway-test-04-provider
     * 10002 -> api-gateway-g5 -> api-gateway-test-05-provider
     * 10002 -> api-gateway-g5 -> api-gateway-test-06-provider
     */
    @PostMapping(value = "distributionGatewayServerNode", produces = "application/json;charset=utf-8")
    public Result<Boolean> distributionGatewayServerNode(@RequestParam String groupId, @RequestParam String gatewayId, @RequestParam String systemId) {
        try {
            logger.info("网关算力与系统挂载配置。groupId：{} gatewayId：{} systemId：{}", groupId, gatewayId, systemId);
            configManageService.distributionGatewayServerNode(groupId, gatewayId, systemId);
            return new Result<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), true);
        } catch (DuplicateKeyException e) {
            logger.warn("查询应用服务配置项信息失败，唯一索引冲突。groupId：{} gatewayId：{} systemId：{}", groupId, gatewayId, systemId, e);
            return new Result<>(ResponseCode.INDEX_DUP.getCode(), e.getMessage(), true);
        } catch (Exception e) {
            logger.error("网关算力与系统挂载配置。groupId：{} gatewayId：{} systemId：{}", groupId, gatewayId, systemId, e);
            return new Result<>(ResponseCode.UN_ERROR.getCode(), e.getMessage(), false);
        }
    }

    @PostMapping(value = "queryApplicationSystemList", produces = "application/json;charset=utf-8")
    public Result<List<ApplicationSystemVO>> queryApplicationSystemList() {
        try {
            logger.info("查询应用服务配置项信息");
            List<ApplicationSystemVO> gatewayServerVOList = configManageService.queryApplicationSystemList();
            return new Result<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), gatewayServerVOList);
        } catch (Exception e) {
            logger.error("查询应用服务配置项信息异常", e);
            return new Result<>(ResponseCode.UN_ERROR.getCode(), e.getMessage(), null);
        }
    }

    @PostMapping(value = "queryApplicationInterfaceList", produces = "application/json;charset=utf-8")
    public Result<List<ApplicationInterfaceVO>> queryApplicationInterfaceList() {
        try {
            logger.info("查询应用接口配置项信息");
            List<ApplicationInterfaceVO> gatewayServerVOList = configManageService.queryApplicationInterfaceList();
            return new Result<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), gatewayServerVOList);
        } catch (Exception e) {
            logger.error("查询应用接口配置项信息异常", e);
            return new Result<>(ResponseCode.UN_ERROR.getCode(), e.getMessage(), null);
        }
    }

    @PostMapping(value = "queryApplicationInterfaceMethodList", produces = "application/json;charset=utf-8")
    public Result<List<ApplicationInterfaceMethodVO>> queryApplicationInterfaceMethodList(){
        try {
            logger.info("查询应用接口方法配置项信息");
            List<ApplicationInterfaceMethodVO> gatewayServerVOList = configManageService.queryApplicationInterfaceMethodList();
            return new Result<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), gatewayServerVOList);
        } catch (Exception e) {
            logger.error("查询应用接口方法配置项信息异常", e);
            return new Result<>(ResponseCode.UN_ERROR.getCode(), e.getMessage(), null);
        }
    }

    @PostMapping(value = "queryApplicationSystemRichInfo", produces = "application/json;charset=utf-8")
    public Result<ApplicationSystemRichInfo> queryApplicationSystemRichInfo(@RequestParam String gatewayId, @RequestParam String systemId) {
        try {
            logger.info("查询分配到网关下的待注册系统信息(系统、接口、方法) gatewayId：{}", gatewayId);
            ApplicationSystemRichInfo applicationSystemRichInfo = configManageService.queryApplicationSystemRichInfo(gatewayId, systemId);
            return new Result<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), applicationSystemRichInfo);
        } catch (Exception e) {
            logger.error("查询分配到网关下的待注册系统信息(系统、接口、方法)异常 gatewayId：{}", gatewayId, e);
            return new Result<>(ResponseCode.UN_ERROR.getCode(), e.getMessage(), null);
        }
    }

    @PostMapping(value = "queryRedisConfig", produces = "application/json;charset=utf-8")
    public Result<Map<String, String>> queryRedisConfig() {
        try {
            logger.info("查询配置中心Redis配置信息");
            Map<String, String> redisConfig = messageService.queryRedisConfig();
            return new Result<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), redisConfig);
        } catch (Exception e) {
            logger.error("查询配置中心Redis配置信息失败", e);
            return new Result<>(ResponseCode.UN_ERROR.getCode(), e.getMessage(), null);
        }
    }

}
