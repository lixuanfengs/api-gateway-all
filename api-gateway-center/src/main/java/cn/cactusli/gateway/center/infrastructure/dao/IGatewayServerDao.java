package cn.cactusli.gateway.center.infrastructure.dao;

import cn.cactusli.gateway.center.infrastructure.common.OperationRequest;
import cn.cactusli.gateway.center.infrastructure.po.GatewayServer;
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
public interface IGatewayServerDao {

    List<GatewayServer> queryGatewayServerList();

    List<GatewayServer> queryGatewayServerListByPage(OperationRequest<String> request);

    int queryGatewayServerListCountByPage(OperationRequest<String> request);


}
