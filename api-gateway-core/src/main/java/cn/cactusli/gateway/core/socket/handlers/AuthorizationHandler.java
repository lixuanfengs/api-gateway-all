package cn.cactusli.gateway.core.socket.handlers;

import cn.cactusli.gateway.core.mapping.HttpStatement;
import cn.cactusli.gateway.core.session.Configuration;
import cn.cactusli.gateway.core.socket.BaseHandler;
import cn.cactusli.gateway.core.socket.agreement.AgreementConstants;
import cn.cactusli.gateway.core.socket.agreement.GatewayResultMessage;
import cn.cactusli.gateway.core.socket.agreement.ResponseParser;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Package: cn.cactusli.gateway.socket.handlers
 * Description:
 *  鉴权
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/19 10:55
 * @Github https://github.com/lixuanfengs
 */
public class AuthorizationHandler extends BaseHandler<FullHttpRequest> {

    private final Logger logger = LoggerFactory.getLogger(AuthorizationHandler.class);

    private final Configuration configuration;

    public AuthorizationHandler(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    protected void session(ChannelHandlerContext ctx, Channel channel, FullHttpRequest request) {
        logger.info("网关接收请求【鉴权】 uri：{} method：{}", request.uri(), request.method());
        try {
            HttpStatement httpStatement = channel.attr(AgreementConstants.HTTP_STATEMENT).get();
            if (httpStatement.isAuth()) {
                try {
                    // 鉴权信息
                    String uId = request.headers().get("uId");
                    String token = request.headers().get("token");
                    // 鉴权判断
                    if (null == token || "".equals(token)) {
                        DefaultFullHttpResponse response = new ResponseParser().parse(GatewayResultMessage.buildError(AgreementConstants.ResponseCode._400.getCode(), "很抱歉，您的 token 不合法"));
                        channel.writeAndFlush(response);
                    }
                    // 鉴权处理 shiro + jwt
                    boolean status = configuration.authValidate(uId, token);
                    // 鉴权成功 直接放行
                    if (status) {
                        request.retain();
                        ctx.fireChannelRead(request);
                    } else {
                        // 鉴权失败
                        DefaultFullHttpResponse response = new ResponseParser().parse(GatewayResultMessage.buildError(AgreementConstants.ResponseCode._403.getCode(), "很抱歉，您无权访问此接口！"));
                        channel.writeAndFlush(response);
                    }

                } catch (Exception e) {
                    DefaultFullHttpResponse response = new ResponseParser().parse(GatewayResultMessage.buildError(AgreementConstants.ResponseCode._403.getCode(), "很抱歉，您的鉴权不合法！"));
                    channel.writeAndFlush(response);
                }
            } else {
                // 不鉴权放行
                request.retain();
                ctx.fireChannelRead(request);
            }
        } catch (Exception e) {
            //  封装返回结果
            DefaultFullHttpResponse response = new ResponseParser().parse(GatewayResultMessage.buildError(AgreementConstants.ResponseCode._500.getCode(), "网关协议调用失败！" + e.getMessage()));
            channel.writeAndFlush(response);
        }
    }
}
