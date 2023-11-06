package cn.cactusli.gateway.center.application;

import cn.cactusli.gateway.center.domain.manage.model.aggregates.ApplicationSystemRichInfo;
import cn.cactusli.gateway.center.domain.manage.model.vo.*;

import java.util.List;

/**
 * Package: cn.cactusli.gateway.center.application
 * Description:
 *  网关配置服务
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/25 11:10
 * @Github https://github.com/lixuanfengs
 */
public interface IConfigManageService {

    List<GatewayServerVO> queryGatewayServerList();

    List<GatewayServerDetailVO> queryGatewayServerDetailList();

    List<GatewayDistributionVO> queryGatewayDistributionList();

    boolean registerGatewayServerNode(String groupId, String gatewayId, String gatewayName, String gatewayAddress);

    ApplicationSystemRichInfo queryApplicationSystemRichInfo(String gatewayId, String systemId);

    String queryGatewayDistribution(String systemId);

    List<ApplicationSystemVO> queryApplicationSystemList();

    List<ApplicationInterfaceVO> queryApplicationInterfaceList();

    List<ApplicationInterfaceMethodVO> queryApplicationInterfaceMethodList();

    void distributionGatewayServerNode(String groupId, String gatewayId, String systemId);

}
