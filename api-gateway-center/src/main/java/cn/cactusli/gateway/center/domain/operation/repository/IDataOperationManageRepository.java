package cn.cactusli.gateway.center.domain.operation.repository;

import cn.cactusli.gateway.center.domain.operation.model.vo.*;
import cn.cactusli.gateway.center.infrastructure.common.OperationRequest;

import java.util.List;

/**
 * Package: cn.cactusli.gateway.center.domain.operation.repository
 * Description:
 *  运营数据查询仓储服务
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/9/27 11:11
 * @Github https://github.com/lixuanfengs
 */
public interface IDataOperationManageRepository {

    List<GatewayServerDataVO> queryGatewayServerListByPage(OperationRequest<String> request);

    int queryGatewayServerListCountByPage(OperationRequest<String> request);

    List<ApplicationSystemDataVO> queryApplicationSystemListByPage(OperationRequest<ApplicationSystemDataVO> request);

    int queryApplicationSystemListCountByPage(OperationRequest<ApplicationSystemDataVO> request);

    List<ApplicationInterfaceDataVO> queryApplicationInterfaceListByPage(OperationRequest<ApplicationInterfaceDataVO> request);

    int queryApplicationInterfaceListCountByPage(OperationRequest<ApplicationInterfaceDataVO> request);

    List<ApplicationInterfaceMethodDataVO> queryApplicationInterfaceMethodListByPage(OperationRequest<ApplicationInterfaceMethodDataVO> request);

    int queryApplicationInterfaceMethodListCountByPage(OperationRequest<ApplicationInterfaceMethodDataVO> request);

    List<GatewayServerDetaiDatalVO> queryGatewayServerDetailListByPage(OperationRequest<GatewayServerDetaiDatalVO> request);

    int queryGatewayServerDetailListCountByPage(OperationRequest<GatewayServerDetaiDatalVO> request);

    List<GatewayDistributionDataVO> queryGatewayDistributionListByPage(OperationRequest<GatewayDistributionDataVO> request);

    int queryGatewayDistributionListCountByPage(OperationRequest<GatewayDistributionDataVO> request);
}
