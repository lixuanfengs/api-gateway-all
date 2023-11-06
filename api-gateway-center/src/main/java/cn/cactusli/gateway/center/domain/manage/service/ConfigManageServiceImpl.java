package cn.cactusli.gateway.center.domain.manage.service;

import cn.cactusli.gateway.center.application.IConfigManageService;
import cn.cactusli.gateway.center.domain.manage.model.aggregates.ApplicationSystemRichInfo;
import cn.cactusli.gateway.center.domain.manage.model.vo.*;
import cn.cactusli.gateway.center.domain.manage.repository.IConfigManageRepository;
import cn.cactusli.gateway.center.infrastructure.common.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Package: cn.cactusli.gateway.center.domain.service
 * Description:
 *  网关配置服务实现类
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/25 11:16
 * @Github https://github.com/lixuanfengs
 */
@Service
public class ConfigManageServiceImpl implements IConfigManageService {

    @Resource
    private IConfigManageRepository configManageRepository;

    @Override
    public List<GatewayServerVO> queryGatewayServerList() {
        return configManageRepository.queryGatewayServerList();
    }

    @Override
    public List<GatewayServerDetailVO> queryGatewayServerDetailList() {
        return configManageRepository.queryGatewayServerDetailList();
    }

    @Override
    public List<GatewayDistributionVO> queryGatewayDistributionList() {
        return configManageRepository.queryGatewayDistributionList();
    }

    @Override
    public boolean registerGatewayServerNode(String groupId, String gatewayId, String gatewayName, String gatewayAddress) {
        GatewayServerDetailVO gatewayServerDetailVO = configManageRepository.queryGatewayServerDetail(gatewayId, gatewayAddress);
        if (null == gatewayServerDetailVO) {
            try {
                return configManageRepository.registerGatewayServerNode(groupId, gatewayId, gatewayName, gatewayAddress, Constants.GatewayStatus.Available);
            } catch (DuplicateKeyException e) {
                return configManageRepository.updateGatewayStatus(gatewayId, gatewayAddress, Constants.GatewayStatus.Available);
            }
        } else {
            return configManageRepository.updateGatewayStatus(gatewayId, gatewayAddress, Constants.GatewayStatus.Available);
        }
    }

    @Override
    public ApplicationSystemRichInfo queryApplicationSystemRichInfo(String gatewayId, String systemId) {
        // 1. 查询出网关ID对应的关联系统ID集合。也就是一个网关ID会被分配一些系统RPC服务注册进来，需要把这些服务查询出来。
        List<String> systemIdList = new ArrayList<>();
        if (null == systemId) {
            systemIdList = configManageRepository.queryGatewayDistributionSystemIdList(gatewayId);
        } else {
            systemIdList.add(systemId);
        }
        // 2. 查询系统ID对应的系统列表信息
        List<ApplicationSystemVO> applicationSystemVOList = configManageRepository.queryApplicationSystemList(systemIdList);
        // 3. 查询系统下的接口信息
        // 思考：这里的查询是一个不断地循环的查询，你是否有办法优化下，减少查询次数。
        for (ApplicationSystemVO applicationSystemVO : applicationSystemVOList) {
            List<ApplicationInterfaceVO> applicationInterfaceVOList = configManageRepository.queryApplicationInterfaceList(applicationSystemVO.getSystemId());
            for (ApplicationInterfaceVO applicationInterfaceVO : applicationInterfaceVOList) {
                List<ApplicationInterfaceMethodVO> applicationInterfaceMethodVOList = configManageRepository.queryApplicationInterfaceMethodList(applicationSystemVO.getSystemId(), applicationInterfaceVO.getInterfaceId());
                applicationInterfaceVO.setMethodList(applicationInterfaceMethodVOList);
            }
            applicationSystemVO.setInterfaceList(applicationInterfaceVOList);
        }
        return new ApplicationSystemRichInfo(gatewayId, applicationSystemVOList);
    }

    @Override
    public String queryGatewayDistribution(String systemId) {
        return configManageRepository.queryGatewayDistribution(systemId);
    }

    @Override
    public List<ApplicationSystemVO> queryApplicationSystemList() {
        return configManageRepository.queryApplicationSystemList(null);
    }

    @Override
    public List<ApplicationInterfaceVO> queryApplicationInterfaceList() {
        return configManageRepository.queryApplicationInterfaceList(null);
    }

    @Override
    public List<ApplicationInterfaceMethodVO> queryApplicationInterfaceMethodList() {
        return configManageRepository.queryApplicationInterfaceMethodList(null, null);
    }

    @Override
    public void distributionGatewayServerNode(String groupId, String gatewayId, String systemId) {
        String systemName = configManageRepository.queryApplicationSystemName(systemId);
        if (StringUtils.isEmpty(systemName))
            throw new RuntimeException("网关算力与系统挂载配置失败，systemId：" + systemId + " 在数据库表「application_system」中不存在！");
        configManageRepository.distributionGatewayServerNode(groupId, gatewayId, systemId, systemName);
    }
}
