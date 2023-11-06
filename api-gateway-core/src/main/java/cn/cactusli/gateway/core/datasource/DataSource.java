package cn.cactusli.gateway.core.datasource;

/**
 * Package: cn.cactusli.gateway.datasource
 * Description:
 *  数据源接口，RPC、HTTP 都当做连接的数据资源使用
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/7 15:07
 * @Github https://github.com/lixuanfengs
 */
public interface DataSource {

    Connection getConnection();

}
