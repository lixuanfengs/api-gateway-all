package cn.cactusli.gateway.center.infrastructure.repository;

import cn.cactusli.gateway.center.domain.manage.model.vo.*;
import cn.cactusli.gateway.center.domain.manage.repository.IConfigManageRepository;
import cn.cactusli.gateway.center.infrastructure.dao.*;
import cn.cactusli.gateway.center.infrastructure.po.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Package: cn.cactusli.gateway.center.infrastructure.repository
 * Description:
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/25 11:23
 * @Github https://github.com/lixuanfengs
 */
@Component
public class ConfigManageRepository implements IConfigManageRepository {

    @Resource
    private IGatewayServerDao gatewayServerDao;
    @Resource
    private IGatewayServerDetailDao gatewayServerDetailDao;
    @Resource
    private IGatewayDistributionDao gatewayDistributionDao;
    @Resource
    private IApplicationSystemDao applicationSystemDao;
    @Resource
    private IApplicationInterfaceDao applicationInterfaceDao;
    @Resource
    private IApplicationInterfaceMethodDao applicationInterfaceMethodDao;


    @Override
    public List<GatewayServerVO> queryGatewayServerList() {
        List<GatewayServer> gatewayServers = gatewayServerDao.queryGatewayServerList();
        List<GatewayServerVO> gatewayServerVOList = new ArrayList<>(gatewayServers.size());
        for (GatewayServer gatewayServer : gatewayServers) {
            // 可以按照 IDEA 插件 vo2dto 方便转换
            GatewayServerVO gatewayServerVO = new GatewayServerVO();
            gatewayServerVO.setGroupId(gatewayServer.getGroupId());
            gatewayServerVO.setGroupName(gatewayServer.getGroupName());
            gatewayServerVOList.add(gatewayServerVO);
        }
        return gatewayServerVOList;
    }

    @Override
    public List<GatewayServerDetailVO> queryGatewayServerDetailList() {
        List<GatewayServerDetail> gatewayServerDetails = gatewayServerDetailDao.queryGatewayServerDetailList();
        List<GatewayServerDetailVO> gatewayServerDetailVOList = new ArrayList<>(gatewayServerDetails.size());
        for (GatewayServerDetail gatewayServerDetail : gatewayServerDetails) {
            GatewayServerDetailVO gatewayServerDetailVO = new GatewayServerDetailVO();
            gatewayServerDetailVO.setId(gatewayServerDetail.getId());
            gatewayServerDetailVO.setGroupId(gatewayServerDetail.getGroupId());
            gatewayServerDetailVO.setGatewayId(gatewayServerDetail.getGatewayId());
            gatewayServerDetailVO.setGatewayName(gatewayServerDetail.getGatewayName());
            gatewayServerDetailVO.setGatewayAddress(gatewayServerDetail.getGatewayAddress());
            gatewayServerDetailVO.setStatus(gatewayServerDetail.getStatus());
            gatewayServerDetailVO.setCreateTime(gatewayServerDetail.getCreateTime());
            gatewayServerDetailVO.setUpdateTime(gatewayServerDetail.getUpdateTime());
            gatewayServerDetailVOList.add(gatewayServerDetailVO);
        }
        return gatewayServerDetailVOList;
    }

    @Override
    public boolean registerGatewayServerNode(String groupId, String gatewayId, String gatewayName, String gatewayAddress, Integer status) {
        GatewayServerDetail gatewayServerDetail = new GatewayServerDetail();
        gatewayServerDetail.setGroupId(groupId);
        gatewayServerDetail.setGatewayId(gatewayId);
        gatewayServerDetail.setGatewayName(gatewayName);
        gatewayServerDetail.setGatewayAddress(gatewayAddress);
        gatewayServerDetail.setStatus(status);
        gatewayServerDetailDao.insert(gatewayServerDetail);
        return true;
    }

    @Override
    public GatewayServerDetailVO queryGatewayServerDetail(String gatewayId, String gatewayAddress) {
        GatewayServerDetail req = new GatewayServerDetail();
        req.setGatewayId(gatewayId);
        req.setGatewayAddress(gatewayAddress);
        GatewayServerDetail gatewayServerDetail = gatewayServerDetailDao.queryGatewayServerDetail(req);
        if (null == gatewayServerDetail) return null;
        // 可以按照 IDEA 插件 vo2dto 方便转换
        GatewayServerDetailVO gatewayServerDetailVO = new GatewayServerDetailVO();
        gatewayServerDetailVO.setGatewayId(gatewayServerDetail.getGatewayId());
        gatewayServerDetailVO.setGatewayName(gatewayServerDetail.getGatewayName());
        gatewayServerDetailVO.setGatewayAddress(gatewayServerDetail.getGatewayAddress());
        gatewayServerDetailVO.setStatus(gatewayServerDetail.getStatus());
        return gatewayServerDetailVO;
    }

    @Override
    public boolean updateGatewayStatus(String gatewayId, String gatewayAddress, Integer available) {
        GatewayServerDetail gatewayServerDetail = new GatewayServerDetail();
        gatewayServerDetail.setGatewayId(gatewayId);
        gatewayServerDetail.setGatewayAddress(gatewayAddress);
        gatewayServerDetail.setStatus(available);
        return gatewayServerDetailDao.updateGatewayStatus(gatewayServerDetail);
    }

    @Override
    public List<String> queryGatewayDistributionSystemIdList(String gatewayId) {
        return gatewayDistributionDao.queryGatewayDistributionSystemIdList();
    }

    @Override
    public List<ApplicationSystemVO> queryApplicationSystemList(List<String> systemIdList) {
        List<ApplicationSystem> applicationSystemList = applicationSystemDao.queryApplicationSystemList(systemIdList);
        List<ApplicationSystemVO> applicationSystemVOList = new ArrayList<>(applicationSystemList.size());
        for (ApplicationSystem applicationSystem : applicationSystemList) {
            ApplicationSystemVO applicationSystemVO = new ApplicationSystemVO();
            applicationSystemVO.setSystemId(applicationSystem.getSystemId());
            applicationSystemVO.setSystemName(applicationSystem.getSystemName());
            applicationSystemVO.setSystemType(applicationSystem.getSystemType());
            applicationSystemVO.setSystemRegistry(applicationSystem.getSystemRegistry());
            applicationSystemVOList.add(applicationSystemVO);
        }
        return applicationSystemVOList;
    }

    @Override
    public List<ApplicationInterfaceVO> queryApplicationInterfaceList(String systemId) {
        List<ApplicationInterface> applicationInterfaces = applicationInterfaceDao.queryApplicationInterfaceList(systemId);
        List<ApplicationInterfaceVO> applicationInterfaceVOList = new ArrayList<>(applicationInterfaces.size());
        for (ApplicationInterface applicationInterface : applicationInterfaces) {
            ApplicationInterfaceVO applicationInterfaceVO = new ApplicationInterfaceVO();
            applicationInterfaceVO.setSystemId(applicationInterface.getSystemId());
            applicationInterfaceVO.setInterfaceId(applicationInterface.getInterfaceId());
            applicationInterfaceVO.setInterfaceName(applicationInterface.getInterfaceName());
            applicationInterfaceVO.setInterfaceVersion(applicationInterface.getInterfaceVersion());
            applicationInterfaceVOList.add(applicationInterfaceVO);
        }
        return applicationInterfaceVOList;
    }

    @Override
    public List<ApplicationInterfaceMethodVO> queryApplicationInterfaceMethodList(String systemId, String interfaceId) {
        ApplicationInterfaceMethod req = new ApplicationInterfaceMethod();
        req.setSystemId(systemId);
        req.setInterfaceId(interfaceId);
        List<ApplicationInterfaceMethod> applicationInterfaceMethods = applicationInterfaceMethodDao.queryApplicationInterfaceMethodList(req);
        List<ApplicationInterfaceMethodVO> applicationInterfaceMethodVOList = new ArrayList<>(applicationInterfaceMethods.size());
        for (ApplicationInterfaceMethod applicationInterfaceMethod : applicationInterfaceMethods) {
            ApplicationInterfaceMethodVO applicationInterfaceMethodVO = new ApplicationInterfaceMethodVO();
            applicationInterfaceMethodVO.setSystemId(applicationInterfaceMethod.getSystemId());
            applicationInterfaceMethodVO.setInterfaceId(applicationInterfaceMethod.getInterfaceId());
            applicationInterfaceMethodVO.setMethodId(applicationInterfaceMethod.getMethodId());
            applicationInterfaceMethodVO.setMethodName(applicationInterfaceMethod.getMethodName());
            applicationInterfaceMethodVO.setParameterType(applicationInterfaceMethod.getParameterType());
            applicationInterfaceMethodVO.setUri(applicationInterfaceMethod.getUri());
            applicationInterfaceMethodVO.setHttpCommandType(applicationInterfaceMethod.getHttpCommandType());
            applicationInterfaceMethodVO.setAuth(applicationInterfaceMethod.getAuth());
            applicationInterfaceMethodVOList.add(applicationInterfaceMethodVO);
        }
        return applicationInterfaceMethodVOList;
    }

    @Override
    public String queryGatewayDistribution(String systemId) {
        return gatewayDistributionDao.queryGatewayDistribution(systemId);
    }

    @Override
    public List<GatewayDistributionVO> queryGatewayDistributionList() {
        List<GatewayDistribution> gatewayDistributions = gatewayDistributionDao.queryGatewayDistributionList();
        List<GatewayDistributionVO> gatewayDistributionVOList = new ArrayList<>(gatewayDistributions.size());
        for (GatewayDistribution gatewayDistribution: gatewayDistributions){
            GatewayDistributionVO gatewayDistributionVO = new GatewayDistributionVO();
            gatewayDistributionVO.setId(gatewayDistribution.getId());
            gatewayDistributionVO.setGroupId(gatewayDistribution.getGroupId());
            gatewayDistributionVO.setGatewayId(gatewayDistribution.getGatewayId());
            gatewayDistributionVO.setSystemId(gatewayDistribution.getSystemId());
            gatewayDistributionVO.setSystemName(gatewayDistribution.getSystemName());
            gatewayDistributionVO.setCreateTime(gatewayDistribution.getCreateTime());
            gatewayDistributionVO.setUpdateTime(gatewayDistribution.getUpdateTime());
            gatewayDistributionVOList.add(gatewayDistributionVO);
        }
        return gatewayDistributionVOList;
    }

    @Override
    public void distributionGatewayServerNode(String groupId, String gatewayId, String systemId, String systemName) {
        GatewayDistributionVO gatewayDistribution = new GatewayDistributionVO();
        gatewayDistribution.setGroupId(groupId);
        gatewayDistribution.setGatewayId(gatewayId);
        gatewayDistribution.setSystemId(systemId);
        gatewayDistribution.setSystemName(systemName);
        gatewayDistributionDao.insert(gatewayDistribution);
    }

    @Override
    public String queryApplicationSystemName(String systemId) {
        return applicationSystemDao.queryApplicationSystemName(systemId);
    }

}
