package cn.cactusli.gateway.center.domain.manage.repository;

import cn.cactusli.gateway.center.domain.manage.model.vo.*;

import java.util.List;

/**
 * Package: cn.cactusli.gateway.center.domain.repository
 * Description:
 *  网关配置仓储服务
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/25 11:17
 * @Github https://github.com/lixuanfengs
 */
public interface IConfigManageRepository {

    List<GatewayServerVO> queryGatewayServerList();

    List<GatewayServerDetailVO> queryGatewayServerDetailList();

    boolean registerGatewayServerNode(String groupId, String gatewayId, String gatewayName, String gatewayAddress, Integer available);

    GatewayServerDetailVO queryGatewayServerDetail(String gatewayId, String gatewayAddress);

    boolean updateGatewayStatus(String gatewayId, String gatewayAddress, Integer available);

    List<String> queryGatewayDistributionSystemIdList(String gatewayId);

    List<ApplicationSystemVO> queryApplicationSystemList(List<String> systemIdList);

    List<ApplicationInterfaceVO> queryApplicationInterfaceList(String systemId);

    List<ApplicationInterfaceMethodVO> queryApplicationInterfaceMethodList(String systemId, String interfaceId);

    String queryGatewayDistribution(String systemId);

    List<GatewayDistributionVO> queryGatewayDistributionList();

    void distributionGatewayServerNode(String groupId, String gatewayId, String systemId, String systemName);

    String queryApplicationSystemName(String systemId);

}
