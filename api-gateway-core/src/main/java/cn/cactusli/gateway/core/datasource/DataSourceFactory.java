package cn.cactusli.gateway.core.datasource;

import cn.cactusli.gateway.core.session.Configuration;

/**
 * Package: cn.cactusli.gateway.datasource
 * Description:
 *  数据源工厂
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/7 15:07
 * @Github https://github.com/lixuanfengs
 */
public interface DataSourceFactory {

    void setProperties(Configuration configuration, String uri);

    DataSource getDataSource();

}
