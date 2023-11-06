package cn.cactusli.gateway.center.test.infrastructure;

import cn.cactusli.gateway.center.CenterApplication;
import cn.cactusli.gateway.center.domain.operation.model.vo.ApplicationSystemDataVO;
import cn.cactusli.gateway.center.domain.operation.model.vo.GatewayServerDataVO;
import cn.cactusli.gateway.center.domain.operation.repository.IDataOperationManageRepository;
import cn.cactusli.gateway.center.infrastructure.common.OperationRequest;
import com.alibaba.fastjson2.JSON;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * Package: cn.cactusli.gateway.center.test.infrastructure
 * Description:
 *  运营管理界面接口测试
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/9/27 17:39
 * @Github https://github.com/lixuanfengs
 */
@SpringBootTest(classes = CenterApplication.class)
public class DataOperationManageRepositoryTest {
    private Logger logger = LoggerFactory.getLogger(DataOperationManageRepositoryTest.class);

    @Resource
    private IDataOperationManageRepository repository;

    @Test
    public void test_queryGatewayServerListByPage() {
        OperationRequest<String> req = new OperationRequest<>(1, 10);
        req.setData("");
        List<GatewayServerDataVO> res = repository.queryGatewayServerListByPage(req);
        logger.info("测试结果 req：{}  res：{}", JSON.toJSONString(req), JSON.toJSONString(res));
    }

    @Test
    public void test_queryGatewayServerListCountByPage() {
        OperationRequest<String> req = new OperationRequest<>(1, 10);
        req.setData("10001");
        int res = repository.queryGatewayServerListCountByPage(req);
        logger.info("测试结果 req：{}  res：{}", JSON.toJSONString(req), JSON.toJSONString(res));
    }

    @Test
    public void test_queryApplicationSystemListByPage(){
        OperationRequest<ApplicationSystemDataVO> req = new OperationRequest<>(1, 10);
        req.setData(new ApplicationSystemDataVO("", "网关sdk测试工程"));
        List<ApplicationSystemDataVO> res = repository.queryApplicationSystemListByPage(req);
        logger.info("测试结果 req：{}  res：{}", JSON.toJSONString(req), JSON.toJSONString(res));
    }
}
