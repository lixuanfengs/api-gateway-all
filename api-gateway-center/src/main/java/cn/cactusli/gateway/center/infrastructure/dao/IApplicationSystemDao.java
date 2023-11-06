package cn.cactusli.gateway.center.infrastructure.dao;

import cn.cactusli.gateway.center.domain.operation.model.vo.ApplicationSystemDataVO;
import cn.cactusli.gateway.center.infrastructure.common.OperationRequest;
import cn.cactusli.gateway.center.infrastructure.po.ApplicationSystem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Package: cn.cactusli.gateway.center.infrastructure.dao
 * Description:
 *  应用系统
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/26 10:21
 * @Github https://github.com/lixuanfengs
 */
@Mapper
public interface IApplicationSystemDao {

    void insert(ApplicationSystem applicationSystem);

    List<ApplicationSystem> queryApplicationSystemList(List<String> list);

    List<ApplicationSystem> queryApplicationSystemListByPage(OperationRequest<ApplicationSystemDataVO> request);

    int queryApplicationSystemListCountByPage(OperationRequest<ApplicationSystemDataVO> request);

    String queryApplicationSystemName(String systemId);


}
