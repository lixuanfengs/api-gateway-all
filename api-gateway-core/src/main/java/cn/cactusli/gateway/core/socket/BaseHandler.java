package cn.cactusli.gateway.core.socket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Package: cn.cactusli.gateway.session
 * Description:
 *  数据处理器基类
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/1 15:28
 * @Github https://github.com/lixuanfengs
 */
public abstract class BaseHandler<T> extends SimpleChannelInboundHandler<T> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, T msg) throws Exception {
        session(ctx, ctx.channel(), msg);
    }

    protected abstract void session(ChannelHandlerContext ctx, final Channel channel, T Request);
}
