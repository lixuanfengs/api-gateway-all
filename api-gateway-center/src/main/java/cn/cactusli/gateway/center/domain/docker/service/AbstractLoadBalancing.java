package cn.cactusli.gateway.center.domain.docker.service;

import cn.cactusli.gateway.center.application.ILoadBalancingService;
import cn.cactusli.gateway.center.domain.docker.model.aggregates.NginxConfig;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

/**
 * Package: cn.cactusli.gateway.center.domain.docker.service
 * Description:
 *  负载均衡抽象类
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/10/7 11:09
 * @Github https://github.com/lixuanfengs
 */
public abstract class AbstractLoadBalancing implements ILoadBalancingService {


    private Logger logger = LoggerFactory.getLogger(AbstractLoadBalancing.class);

    @Value("${nginx.conf-path-docker}")
    private String conf_path_docker;

    @Override
    public void updateNginxConfig(NginxConfig nginxConfig) throws Exception {
        // 1. 创建 Nginx 配置文件
        String nginxConfigFile = createNginxConfigFile(nginxConfig);
        logger.info("步骤1：创建 Nginx 配置文件 containerFilePath：{}", nginxConfigFile);
        // 2. 复制 Nginx 配置文件
        // copyDockerFile(nginxConfig.getApplicationName(), containerFilePath, nginxConfig.getLocalNginxPath());
        // logger.info("步骤2：拷贝 Nginx 配置文件 localPath：{}", nginxConfig.getLocalNginxPath());

        // 2.1 复制 Nginx 配置文件到 Docker 容器中指定的目录下 （方便 window 环境下调试）
        if (StringUtils.isNotEmpty(conf_path_docker)) {
            copyFileToDocker(nginxConfig.getNginxName(), nginxConfigFile, conf_path_docker);
        }

        // 3. 刷新 Nginx 配置文件
        refreshNginxConfig(nginxConfig.getNginxName());
        logger.info("步骤2：刷新 Nginx 配置文件 Done！");

    }

    protected abstract String createNginxConfigFile(NginxConfig nginxConfig) throws IOException;

    protected abstract void copyDockerFile(String applicationName, String containerFilePath, String localNginxPath) throws InterruptedException, IOException;

    protected abstract void copyFileToDocker(String nginxName, String containerFilePath, String localNginxPath) throws IOException, InterruptedException;

    protected abstract void refreshNginxConfig(String nginxName) throws InterruptedException, IOException;


}
