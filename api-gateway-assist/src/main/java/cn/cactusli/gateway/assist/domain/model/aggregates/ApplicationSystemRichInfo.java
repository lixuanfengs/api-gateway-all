package cn.cactusli.gateway.assist.domain.model.aggregates;

import cn.cactusli.gateway.assist.domain.model.vo.ApplicationSystemVO;

import java.util.List;

/**
 * Package: cn.cactusli.gateway.assist.domain.model.aggregates
 * Description:
 *  网关算力配置信息
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/8/7 10:11
 * @Github https://github.com/lixuanfengs
 */
public class ApplicationSystemRichInfo {

    /** 网关ID */
    private String gatewayId;
    /** 系统列表 */
    private List<ApplicationSystemVO> applicationSystemVOList;

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
