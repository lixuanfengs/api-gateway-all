package cn.cactusli.gateway.center.domain.register.service;

import cn.cactusli.gateway.center.application.IRegisterManageService;
import cn.cactusli.gateway.center.domain.register.model.vo.ApplicationInterfaceMethodVO;
import cn.cactusli.gateway.center.domain.register.model.vo.ApplicationInterfaceVO;
import cn.cactusli.gateway.center.domain.register.model.vo.ApplicationSystemVO;
import cn.cactusli.gateway.center.domain.register.repository.IRegisterManageRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Package: cn.cactusli.gateway.center.domain.register.service
 * Description:
 *  接口注册服务实现类
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/26 10:32
 * @Github https://github.com/lixuanfengs
 */
@Service
public class IRegisterManageServiceImpl implements IRegisterManageService {


    @Resource
    private IRegisterManageRepository registerManageRepository;

    @Override
    public void registerApplication(ApplicationSystemVO applicationSystemVO) {
        registerManageRepository.registerApplication(applicationSystemVO);
    }

    @Override
    public void registerApplicationInterface(ApplicationInterfaceVO applicationInterfaceVO) {
        registerManageRepository.registerApplicationInterface(applicationInterfaceVO);
    }

    @Override
    public void registerApplicationInterfaceMethod(ApplicationInterfaceMethodVO applicationInterfaceMethodVO) {
        registerManageRepository.registerApplicationInterfaceMethod(applicationInterfaceMethodVO);
    }
}
