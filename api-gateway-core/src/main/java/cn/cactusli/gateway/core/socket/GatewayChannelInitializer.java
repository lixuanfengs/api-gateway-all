package cn.cactusli.gateway.core.socket;

import cn.cactusli.gateway.core.session.Configuration;
import cn.cactusli.gateway.core.session.defaults.DefaultGatewaySessionFactory;
import cn.cactusli.gateway.core.socket.handlers.GatewayServerHandler;
import cn.cactusli.gateway.core.socket.handlers.ProtocolDataHandler;
import cn.cactusli.gateway.core.socket.handlers.AuthorizationHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * Package: cn.cactusli.gateway.session
 * Description:
 *  构建管道，在管道里放一些板子
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/1 15:47
 * @Github https://github.com/lixuanfengs
 */
public class GatewayChannelInitializer extends ChannelInitializer<SocketChannel> {

    private final Configuration configuration;

    private final DefaultGatewaySessionFactory defaultGatewaySessionFactory;

    public GatewayChannelInitializer(Configuration configuration, DefaultGatewaySessionFactory defaultGatewaySessionFactory) {
        this.configuration = configuration;
        this.defaultGatewaySessionFactory = defaultGatewaySessionFactory;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline line = socketChannel.pipeline();
        line.addLast(new HttpRequestDecoder());
        line.addLast(new HttpResponseEncoder());
        line.addLast(new HttpObjectAggregator(1024 * 1024));
        line.addLast(new GatewayServerHandler(configuration));
        line.addLast(new AuthorizationHandler(configuration));
        line.addLast(new ProtocolDataHandler(defaultGatewaySessionFactory));
    }
}
