package cn.cactusli.gateway.center.interfaces;

import cn.cactusli.gateway.center.application.IConfigManageService;
import cn.cactusli.gateway.center.application.IMessageService;
import cn.cactusli.gateway.center.application.IRegisterManageService;
import cn.cactusli.gateway.center.domain.register.model.vo.ApplicationInterfaceMethodVO;
import cn.cactusli.gateway.center.domain.register.model.vo.ApplicationInterfaceVO;
import cn.cactusli.gateway.center.domain.register.model.vo.ApplicationSystemVO;
import cn.cactusli.gateway.center.infrastructure.common.ResponseCode;
import cn.cactusli.gateway.center.infrastructure.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Package: cn.cactusli.gateway.center.interfaces
 * Description:
 *  RPC 服务注册管理
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/26 10:38
 * @Github https://github.com/lixuanfengs
 */
@RestController
@RequestMapping("/cactus/admin/register")
public class RpcRegisterManage {

    private Logger logger = LoggerFactory.getLogger(RpcRegisterManage.class);

    @Resource
    private IRegisterManageService registerManageService;
    @Resource
    private IConfigManageService configManageService;
    @Resource
    private IMessageService messageService;

    @PostMapping(value = "registerApplication", produces = "application/json;charset=utf-8")
    public Result<Boolean> registerApplication(@RequestParam String systemId,
                                               @RequestParam String systemName,
                                               @RequestParam String systemType,
                                               @RequestParam String systemRegistry) {
        try {
            logger.info("注册应用服务 systemId：{}", systemId);
            ApplicationSystemVO applicationSystemVO = new ApplicationSystemVO();
            applicationSystemVO.setSystemId(systemId);
            applicationSystemVO.setSystemName(systemName);
            applicationSystemVO.setSystemType(systemType);
            applicationSystemVO.setSystemRegistry(systemRegistry);
            registerManageService.registerApplication(applicationSystemVO);
            return new Result<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), true);
        } catch (DuplicateKeyException e) {
            logger.warn("注册应用服务重复 systemId：{}", systemId, e);
            return new Result<>(ResponseCode.INDEX_DUP.getCode(), e.getMessage(), true);
        } catch (Exception e) {
            logger.error("注册应用服务失败 systemId：{}", systemId, e);
            return new Result<>(ResponseCode.UN_ERROR.getCode(), e.getMessage(), false);
        }
    }

    @PostMapping(value = "registerApplicationInterface", produces = "application/json;charset=utf-8")
    public Result<Boolean> registerApplicationInterface(@RequestParam String systemId,
                                                        @RequestParam String interfaceId,
                                                        @RequestParam String interfaceName,
                                                        @RequestParam String interfaceVersion) {
        try {
            logger.info("注册应用接口 systemId：{} interfaceId：{}", systemId, interfaceId);
            ApplicationInterfaceVO applicationInterfaceVO = new ApplicationInterfaceVO();
            applicationInterfaceVO.setSystemId(systemId);
            applicationInterfaceVO.setInterfaceId(interfaceId);
            applicationInterfaceVO.setInterfaceName(interfaceName);
            applicationInterfaceVO.setInterfaceVersion(interfaceVersion);
            registerManageService.registerApplicationInterface(applicationInterfaceVO);
            return new Result<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), true);
        } catch (DuplicateKeyException e) {
            logger.warn("注册应用接口重复 systemId：{} interfaceId：{}", systemId, interfaceId);
            return new Result<>(ResponseCode.INDEX_DUP.getCode(), e.getMessage(), true);
        } catch (Exception e) {
            logger.error("注册应用接口失败 systemId：{}", systemId, e);
            return new Result<>(ResponseCode.UN_ERROR.getCode(), e.getMessage(), false);
        }
    }

    @PostMapping(value = "registerApplicationInterfaceMethod", produces = "application/json;charset=utf-8")
    public Result<Boolean> registerApplicationInterfaceMethod(@RequestParam String systemId,
                                                              @RequestParam String interfaceId,
                                                              @RequestParam String methodId,
                                                              @RequestParam String methodName,
                                                              @RequestParam String parameterType,
                                                              @RequestParam String uri,
                                                              @RequestParam String httpCommandType,
                                                              @RequestParam Integer auth) {
        try {
            logger.info("注册应用接口方法 systemId：{} interfaceId：{} methodId：{}", systemId, interfaceId, methodId);
            ApplicationInterfaceMethodVO applicationInterfaceVO = new ApplicationInterfaceMethodVO();
            applicationInterfaceVO.setSystemId(systemId);
            applicationInterfaceVO.setInterfaceId(interfaceId);
            applicationInterfaceVO.setMethodId(methodId);
            applicationInterfaceVO.setMethodName(methodName);
            applicationInterfaceVO.setParameterType(parameterType);
            applicationInterfaceVO.setUri(uri);
            applicationInterfaceVO.setHttpCommandType(httpCommandType);
            applicationInterfaceVO.setAuth(auth);
            registerManageService.registerApplicationInterfaceMethod(applicationInterfaceVO);
            return new Result<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), true);
        } catch (DuplicateKeyException e) {
            logger.warn("注册应用接口重复 systemId：{} interfaceId：{}", systemId, interfaceId);
            return new Result<>(ResponseCode.INDEX_DUP.getCode(), e.getMessage(), true);
        } catch (Exception e) {
            logger.error("注册应用接口失败 systemId：{}", systemId, e);
            return new Result<>(ResponseCode.UN_ERROR.getCode(), e.getMessage(), false);
        }
    }

    @PostMapping(value = "registerEvent", produces = "application/json;charset=utf-8")
    public Result<Boolean> registerEvent(@RequestParam String systemId) {
        try {
            logger.info("应用信息注册完成通知 systemId：{}", systemId);
            // 推送消息
            String gatewayId = configManageService.queryGatewayDistribution(systemId);
            messageService.pushMessage(gatewayId, systemId);
            return new Result<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), true);
        } catch (Exception e) {
            logger.error("应用信息注册完成通知失败 systemId：{}", systemId, e);
            return new Result<>(ResponseCode.UN_ERROR.getCode(), e.getMessage(), false);
        }
    }

}
