package cn.cactusli.gateway.center.application;

import cn.cactusli.gateway.center.domain.register.model.vo.ApplicationInterfaceMethodVO;
import cn.cactusli.gateway.center.domain.register.model.vo.ApplicationInterfaceVO;
import cn.cactusli.gateway.center.domain.register.model.vo.ApplicationSystemVO;

/**
 * Package: cn.cactusli.gateway.center.application
 * Description:
 *  接口注册服务
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/26 10:26
 * @Github https://github.com/lixuanfengs
 */
public interface IRegisterManageService {

    void registerApplication(ApplicationSystemVO applicationSystemVO);

    void registerApplicationInterface(ApplicationInterfaceVO applicationInterfaceVO);

    void registerApplicationInterfaceMethod(ApplicationInterfaceMethodVO applicationInterfaceMethodVO);

}
