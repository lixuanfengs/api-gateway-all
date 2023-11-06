package cn.cactusli.gateway.core.session.defaults;

import cn.cactusli.gateway.core.datasource.DataSource;
import cn.cactusli.gateway.core.datasource.DataSourceFactory;
import cn.cactusli.gateway.core.datasource.unpooled.UnpooledDataSourceFactory;
import cn.cactusli.gateway.core.executor.Executor;
import cn.cactusli.gateway.core.session.GatewaySession;
import cn.cactusli.gateway.core.session.Configuration;
import cn.cactusli.gateway.core.session.GatewaySessionFactory;

/**
 * Package: cn.cactusli.gateway.session.defaults
 * Description:
 *  默认网关会话工厂
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/6 11:08
 * @Github https://github.com/lixuanfengs
 */
public class DefaultGatewaySessionFactory implements GatewaySessionFactory {

    private final Configuration configuration;

    public DefaultGatewaySessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public GatewaySession openSession(String uri) {
        // 获取数据源连接信息：这里把 Dubbo、HTTP 抽象为一种连接资源
        DataSourceFactory dataSourceFactory = new UnpooledDataSourceFactory();
        dataSourceFactory.setProperties(configuration, uri);
        DataSource dataSource = dataSourceFactory.getDataSource();
        // 创建执行器
        Executor executor = configuration.newExecutor(dataSource.getConnection());
        // 创建会话：DefaultGatewaySession
        return new DefaultGatewaySession(configuration, uri, executor);
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
