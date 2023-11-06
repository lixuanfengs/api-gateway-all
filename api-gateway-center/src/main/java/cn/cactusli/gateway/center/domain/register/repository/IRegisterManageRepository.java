package cn.cactusli.gateway.center.domain.register.repository;

import cn.cactusli.gateway.center.domain.register.model.vo.ApplicationInterfaceMethodVO;
import cn.cactusli.gateway.center.domain.register.model.vo.ApplicationInterfaceVO;
import cn.cactusli.gateway.center.domain.register.model.vo.ApplicationSystemVO;

/**
 * Package: cn.cactusli.gateway.center.domain.register.repository
 * Description:
 *  接口注册仓储服务
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/26 10:32
 * @Github https://github.com/lixuanfengs
 */
public interface IRegisterManageRepository {

    void registerApplication(ApplicationSystemVO applicationSystemVO);

    void registerApplicationInterface(ApplicationInterfaceVO applicationInterfaceVO);

    void registerApplicationInterfaceMethod(ApplicationInterfaceMethodVO applicationInterfaceMethodVO);

}
