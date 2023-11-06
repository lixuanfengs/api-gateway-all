package cn.cactusli.gateway.center.application;

import cn.cactusli.gateway.center.domain.docker.model.aggregates.NginxConfig;

/**
 * Package: cn.cactusli.gateway.center.application
 * Description:
 *  负载均衡配置服务
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/10/7 10:57
 * @Github https://github.com/lixuanfengs
 */
public interface ILoadBalancingService {
    void updateNginxConfig(NginxConfig nginxConfig) throws Exception;
}
