package cn.cactusli.gateway.center.domain.manage.model.aggregates;

import cn.cactusli.gateway.center.domain.manage.model.vo.ApplicationSystemVO;

import java.util.List;

/**
 * Package: cn.cactusli.gateway.center.domain.manage.model.aggregates
 * Description:
 *   网关算力配置信息
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/7/28 14:51
 * @Github https://github.com/lixuanfengs
 */
public class ApplicationSystemRichInfo {

    /** 网关ID */
    private String gatewayId;
    /** 系统列表 */
    private List<ApplicationSystemVO> applicationSystemVOList;

    public ApplicationSystemRichInfo() {
    }

    public ApplicationSystemRichInfo(String gatewayId, List<ApplicationSystemVO> applicationSystemVOList) {
        this.gatewayId = gatewayId;
        this.applicationSystemVOList = applicationSystemVOList;
    }

    public String getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
    }

    public List<ApplicationSystemVO> getApplicationSystemVOList() {
        return applicationSystemVOList;
    }

    public void setApplicationSystemVOList(List<ApplicationSystemVO> applicationSystemVOList) {
        this.applicationSystemVOList = applicationSystemVOList;
    }


}
