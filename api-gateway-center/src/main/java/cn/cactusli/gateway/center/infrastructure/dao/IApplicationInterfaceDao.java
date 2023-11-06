package cn.cactusli.gateway.center.infrastructure.dao;

import cn.cactusli.gateway.center.domain.operation.model.vo.ApplicationInterfaceDataVO;
import cn.cactusli.gateway.center.infrastructure.common.OperationRequest;
import cn.cactusli.gateway.center.infrastructure.po.ApplicationInterface;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Package: cn.cactusli.gateway.center.infrastructure.dao
 * Description:
 *  应用接口
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/26 10:20
 * @Github https://github.com/lixuanfengs
 */
@Mapper
public interface IApplicationInterfaceDao {
    void insert(ApplicationInterface applicationInterface);

    List<ApplicationInterface> queryApplicationInterfaceList(String systemId);

    List<ApplicationInterface> queryApplicationInterfaceListByPage(OperationRequest<ApplicationInterfaceDataVO> request);

    int queryApplicationInterfaceListCountByPage(OperationRequest<ApplicationInterfaceDataVO> request);
}
