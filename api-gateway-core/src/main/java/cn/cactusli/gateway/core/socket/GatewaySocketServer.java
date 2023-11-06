package cn.cactusli.gateway.core.socket;

import cn.cactusli.gateway.core.session.Configuration;
import cn.cactusli.gateway.core.session.defaults.DefaultGatewaySessionFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Callable;

/**
 * Package: cn.cactusli.gateway.session
 * Description:
 *  网关会话服务
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/1 15:47
 * @Github https://github.com/lixuanfengs
 */
public class GatewaySocketServer implements Callable<Channel> {

    private final Logger logger = LoggerFactory.getLogger(GatewaySocketServer.class);

    private final Configuration configuration;

    private DefaultGatewaySessionFactory defaultGatewaySessionFactory;

    private EventLoopGroup boss;
    private EventLoopGroup work;

    private Channel channel;

    public GatewaySocketServer(Configuration configuration, DefaultGatewaySessionFactory defaultGatewaySessionFactory) {
        this.configuration = configuration;
        this.defaultGatewaySessionFactory = defaultGatewaySessionFactory;
        this.initEventLoopGroup();
    }

    private void initEventLoopGroup(){
        boss = new NioEventLoopGroup(configuration.getBossNThreads());
        work = new NioEventLoopGroup(configuration.getWorkNThreads());
    }

    @Override
    public Channel call() throws Exception {
        ChannelFuture channelFuture = null;
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss, work)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childHandler(new GatewayChannelInitializer(configuration, defaultGatewaySessionFactory));
            // Docker 容器部署会自动分配IP，所以我们只设定端口即可。
            // channelFuture = bootstrap.bind(new InetSocketAddress(configuration.getHostName(),8899)).syncUninterruptibly();
            channelFuture = bootstrap.bind(configuration.getPort()).syncUninterruptibly();
            this.channel = channelFuture.channel();
        } catch (Exception e) {
            logger.error("socket server start error.", e);
        } finally {
            if (null != channelFuture && channelFuture.isSuccess()) {
                logger.info("socket server start done.");
            } else {
                logger.error("socket server start error.");
            }
        }
        return channel;
    }
}
