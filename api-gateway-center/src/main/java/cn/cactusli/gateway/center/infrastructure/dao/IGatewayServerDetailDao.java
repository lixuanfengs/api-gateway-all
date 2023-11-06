package cn.cactusli.gateway.center.infrastructure.dao;

import cn.cactusli.gateway.center.domain.operation.model.vo.GatewayServerDetaiDatalVO;
import cn.cactusli.gateway.center.infrastructure.common.OperationRequest;
import cn.cactusli.gateway.center.infrastructure.po.GatewayServerDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Package: cn.cactusli.gateway.center.infrastructure.dao
 * Description:
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/25 11:27
 * @Github https://github.com/lixuanfengs
 */
@Mapper
public interface IGatewayServerDetailDao {

    void insert(GatewayServerDetail gatewayServerDetail);

    GatewayServerDetail queryGatewayServerDetail(GatewayServerDetail gatewayServerDetail);

    boolean updateGatewayStatus(GatewayServerDetail gatewayServerDetail);

    List<GatewayServerDetail> queryGatewayServerDetailList();

    List<GatewayServerDetail> queryGatewayServerDetailListByPage(OperationRequest<GatewayServerDetaiDatalVO> request);

    int queryGatewayServerDetailListCountByPage(OperationRequest<GatewayServerDetaiDatalVO> request);
}
