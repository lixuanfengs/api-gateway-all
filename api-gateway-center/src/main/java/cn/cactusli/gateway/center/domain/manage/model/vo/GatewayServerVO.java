package cn.cactusli.gateway.center.domain.manage.model.vo;

/**
 * Package: cn.cactusli.gateway.center.domain.model.aggregates
 * Description:
 *  网关服务配置
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/25 11:13
 * @Github https://github.com/lixuanfengs
 */
public class GatewayServerVO {

    /** 分组标识 */
    private String groupId;
    /** 分组名称 */
    private String groupName;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
