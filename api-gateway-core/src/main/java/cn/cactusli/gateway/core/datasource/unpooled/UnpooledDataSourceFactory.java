package cn.cactusli.gateway.core.datasource.unpooled;

import cn.cactusli.gateway.core.datasource.DataSource;
import cn.cactusli.gateway.core.datasource.DataSourceFactory;
import cn.cactusli.gateway.core.datasource.DataSourceType;
import cn.cactusli.gateway.core.session.Configuration;

/**
 * Package: cn.cactusli.gateway.datasource.unpooled
 * Description:
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/7 15:19
 * @Github https://github.com/lixuanfengs
 */
public class UnpooledDataSourceFactory implements DataSourceFactory {

    protected UnpooledDataSource dataSource;

    public UnpooledDataSourceFactory() {
        this.dataSource = new UnpooledDataSource();
    }

    @Override
    public void setProperties(Configuration configuration, String uri) {
        this.dataSource.setConfiguration(configuration);
        this.dataSource.setDataSourceType(DataSourceType.Dubbo);
        this.dataSource.setHttpStatement(configuration.getHttpStatement(uri));
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }
}
