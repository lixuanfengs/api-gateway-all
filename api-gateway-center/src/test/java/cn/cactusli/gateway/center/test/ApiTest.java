package cn.cactusli.gateway.center.test;

import cn.cactusli.gateway.center.CenterApplication;
import cn.cactusli.gateway.center.application.IConfigManageService;
import cn.cactusli.gateway.center.application.IRegisterManageService;
import cn.cactusli.gateway.center.domain.manage.model.aggregates.ApplicationSystemRichInfo;
import cn.cactusli.gateway.center.domain.manage.model.vo.GatewayServerVO;
import cn.cactusli.gateway.center.domain.message.Publisher;
import cn.cactusli.gateway.center.domain.register.model.vo.ApplicationInterfaceMethodVO;
import cn.cactusli.gateway.center.domain.register.model.vo.ApplicationInterfaceVO;
import cn.cactusli.gateway.center.domain.register.model.vo.ApplicationSystemVO;
import com.alibaba.fastjson2.JSON;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * Package: cn.cactusli.gateway.center
 * Description:
 *  单元测试
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/20 15:21
 * @Github https://github.com/lixuanfengs
 */
@SpringBootTest(classes = CenterApplication.class)
public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Resource
    private IConfigManageService configManageService;

    @Resource
    private IRegisterManageService registerManageService;

    @Test
    public void test() {
        List<GatewayServerVO> apiDataList = configManageService.queryGatewayServerList();
        logger.info("测试结果：{}", JSON.toJSONString(apiDataList));
    }

    @Test
    public void test_registerGatewayServerNode() {
        configManageService.registerGatewayServerNode("10008", "api-gateway-g1", "电商支付网关", "127.0.0.196");
        configManageService.registerGatewayServerNode("10008", "api-gateway-g2", "电商支付网关", "127.0.0.197");
        configManageService.registerGatewayServerNode("10008", "api-gateway-g3", "电商配送网关", "127.0.0.198");
    }

    @Test
    public void test_registerApplication() {
        ApplicationSystemVO applicationSystemVO = new ApplicationSystemVO();
        applicationSystemVO.setSystemId("api-gateway-test-provider-01");
        applicationSystemVO.setSystemName("网关测试系统");
        applicationSystemVO.setSystemType("RPC");
        applicationSystemVO.setSystemRegistry("127.0.0.1");
        registerManageService.registerApplication(applicationSystemVO);
    }

    @Test
    public void test_registerApplicationInterface() {
        ApplicationInterfaceVO applicationInterfaceVO = new ApplicationInterfaceVO();
        applicationInterfaceVO.setSystemId("api-gateway-test");
        applicationInterfaceVO.setInterfaceId("cn.cactusli.gateway.rpc.IActivityBooth");
        applicationInterfaceVO.setInterfaceName("活动平台");
        applicationInterfaceVO.setInterfaceVersion("v1.0.0");
        registerManageService.registerApplicationInterface(applicationInterfaceVO);
    }

    @Test
    public void test_registerApplicationInterfaceMethod() {
        ApplicationInterfaceMethodVO applicationInterfaceVO01 = new ApplicationInterfaceMethodVO();
        applicationInterfaceVO01.setSystemId("api-gateway-test");
        applicationInterfaceVO01.setInterfaceId("cn.cactusli.gateway.rpc.IActivityBooth");
        applicationInterfaceVO01.setMethodId("sayHello");
        applicationInterfaceVO01.setMethodName("测试方法");
        applicationInterfaceVO01.setParameterType("java.lang.String");
        applicationInterfaceVO01.setUri("/cactus/activity/sayHello");
        applicationInterfaceVO01.setHttpCommandType("GET");
        applicationInterfaceVO01.setAuth(0);
        registerManageService.registerApplicationInterfaceMethod(applicationInterfaceVO01);

        ApplicationInterfaceMethodVO applicationInterfaceVO02 = new ApplicationInterfaceMethodVO();
        applicationInterfaceVO02.setSystemId("api-gateway-test");
        applicationInterfaceVO02.setInterfaceId("cn.cactusli.gateway.rpc.IActivityBooth");
        applicationInterfaceVO02.setMethodId("insert");
        applicationInterfaceVO02.setMethodName("插入方法");
        applicationInterfaceVO02.setParameterType("cn.cactusli.gateway.rpc.dto.XReq");
        applicationInterfaceVO02.setUri("/cactus/activity/insert");
        applicationInterfaceVO02.setHttpCommandType("POST");
        applicationInterfaceVO02.setAuth(1);
        registerManageService.registerApplicationInterfaceMethod(applicationInterfaceVO02);
    }

    @Test
    public void test_queryApplicationSystemRichInfo(){
        ApplicationSystemRichInfo result = configManageService.queryApplicationSystemRichInfo("api-gateway-g4", "testq11");
        logger.info("测试结果：{}", JSON.toJSONString(result));
    }



    @Resource
    private Publisher publisher;

    @Test
    public void test_messages() throws InterruptedException {
        publisher.pushMessage("api-gateway-g4", "api-gateway-test-provider");
        Thread.sleep(50000);
    }


}
