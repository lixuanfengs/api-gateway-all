package cn.cactusli.gateway.center.infrastructure.dao;

import cn.cactusli.gateway.center.domain.manage.model.vo.GatewayDistributionVO;
import cn.cactusli.gateway.center.domain.operation.model.vo.GatewayDistributionDataVO;
import cn.cactusli.gateway.center.infrastructure.common.OperationRequest;
import cn.cactusli.gateway.center.infrastructure.po.GatewayDistribution;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Package: cn.cactusli.gateway.center.infrastructure.dao
 * Description:
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/7/28 14:58
 * @Github https://github.com/lixuanfengs
 */
@Mapper
public interface IGatewayDistributionDao {

    List<String> queryGatewayDistributionSystemIdList();

    String queryGatewayDistribution(String systemId);

    List<GatewayDistribution> queryGatewayDistributionList();

    List<GatewayDistribution> queryGatewayDistributionListByPage(OperationRequest<GatewayDistributionDataVO> request);

    int queryGatewayDistributionListCountByPage(OperationRequest<GatewayDistributionDataVO> request);

    void insert(GatewayDistributionVO gatewayDistribution);


}
