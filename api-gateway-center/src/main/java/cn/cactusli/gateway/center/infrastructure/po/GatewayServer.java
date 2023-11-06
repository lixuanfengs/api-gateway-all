package cn.cactusli.gateway.center.infrastructure.po;

/**
 * Package: cn.cactusli.gateway.center.infrastructure.po
 * Description:
 *  网关服务
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/25 11:21
 * @Github https://github.com/lixuanfengs
 */
public class GatewayServer {

    /** 自增主键 */
    private Integer id;
    /** 分组标识 */
    private String groupId;
    /** 分组名称 */
    private String groupName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
