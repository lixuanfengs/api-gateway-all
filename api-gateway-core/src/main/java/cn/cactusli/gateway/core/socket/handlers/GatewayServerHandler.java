package cn.cactusli.gateway.core.socket.handlers;

import cn.cactusli.gateway.core.mapping.HttpStatement;
import cn.cactusli.gateway.core.session.Configuration;
import cn.cactusli.gateway.core.socket.BaseHandler;
import cn.cactusli.gateway.core.socket.agreement.AgreementConstants;
import cn.cactusli.gateway.core.socket.agreement.RequestParser;
import cn.cactusli.gateway.core.socket.agreement.ResponseParser;
import cn.cactusli.gateway.core.socket.agreement.GatewayResultMessage;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Package: cn.cactusli.gateway.socket.handlers
 * Description:
 *  会话服务处理器
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/6 11:11
 * @Github https://github.com/lixuanfengs
 */
public class GatewayServerHandler extends BaseHandler<FullHttpRequest> {

    private final Logger logger = LoggerFactory.getLogger(GatewayServerHandler.class);

    private final Configuration configuration;

    public GatewayServerHandler(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    protected void session(ChannelHandlerContext ctx, Channel channel, FullHttpRequest request) {
        logger.info("网关接收请求【全局】 uri：{} method：{}", request.uri(), request.method());
        try {
            // 解析请求参数
            RequestParser requestParser = new RequestParser(request);
            String uri = requestParser.getUri();
            // 保存信息；HttpStatement、Header=token
            HttpStatement httpStatement = configuration.getHttpStatement(uri);
            channel.attr(AgreementConstants.HTTP_STATEMENT).set(httpStatement);

            // 放行服务
            request.retain();
            ctx.fireChannelRead(request);
        } catch (Exception e) {
            // 封装返回结果
            DefaultFullHttpResponse response = new ResponseParser().parse(GatewayResultMessage.buildError(AgreementConstants.ResponseCode._500.getCode(), "网关协议调用失败！" + e.getMessage()));
            channel.writeAndFlush(response);
        }
    }
}
