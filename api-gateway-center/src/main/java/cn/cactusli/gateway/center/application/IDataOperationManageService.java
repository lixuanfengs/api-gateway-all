package cn.cactusli.gateway.center.application;

import cn.cactusli.gateway.center.domain.operation.model.vo.*;
import cn.cactusli.gateway.center.infrastructure.common.OperationRequest;
import cn.cactusli.gateway.center.infrastructure.common.OperationResult;

/**
 * Package: cn.cactusli.gateway.center.application
 * Description:
 *  网关运营数据管理
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/9/27 11:00
 * @Github https://github.com/lixuanfengs
 */
public interface IDataOperationManageService {

    OperationResult<GatewayServerDataVO> queryGatewayServer(OperationRequest<String> request);

    OperationResult<ApplicationSystemDataVO> queryApplicationSystem(OperationRequest<ApplicationSystemDataVO> request);

    OperationResult<ApplicationInterfaceDataVO> queryApplicationInterface(OperationRequest<ApplicationInterfaceDataVO> request);

    OperationResult<ApplicationInterfaceMethodDataVO> queryApplicationInterfaceMethod(OperationRequest<ApplicationInterfaceMethodDataVO> request);

    OperationResult<GatewayServerDetaiDatalVO> queryGatewayServerDetail(OperationRequest<GatewayServerDetaiDatalVO> request);

    OperationResult<GatewayDistributionDataVO> queryGatewayDistribution(OperationRequest<GatewayDistributionDataVO> request);
}
