package cn.cactusli.gateway.core.socket.handlers;

import cn.cactusli.gateway.core.bind.IGenericReference;
import cn.cactusli.gateway.core.executor.result.SessionResult;
import cn.cactusli.gateway.core.session.GatewaySession;
import cn.cactusli.gateway.core.session.defaults.DefaultGatewaySessionFactory;
import cn.cactusli.gateway.core.socket.BaseHandler;
import cn.cactusli.gateway.core.socket.agreement.RequestParser;
import cn.cactusli.gateway.core.socket.agreement.AgreementConstants;
import cn.cactusli.gateway.core.socket.agreement.GatewayResultMessage;
import cn.cactusli.gateway.core.socket.agreement.ResponseParser;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Package: cn.cactusli.gateway.socket.handlers
 * Description:
 *  协议数据处理
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/19 11:16
 * @Github https://github.com/lixuanfengs
 */
public class ProtocolDataHandler extends BaseHandler<FullHttpRequest> {

    private final Logger logger = LoggerFactory.getLogger(ProtocolDataHandler.class);

    private final DefaultGatewaySessionFactory gatewaySessionFactory;

    public ProtocolDataHandler(DefaultGatewaySessionFactory gatewaySessionFactory) {
        this.gatewaySessionFactory = gatewaySessionFactory;
    }

    @Override
    protected void session(ChannelHandlerContext ctx, Channel channel, FullHttpRequest request) {
        logger.info("网关接收请求【消息】 uri：{} method：{}", request.uri(), request.method());
        try {
            // 解析请求参数
            RequestParser requestParser = new RequestParser(request);
            String uri = requestParser.getUri();
            if (null == uri) return;
            Map<String, Object> args = requestParser.parse();
            
            // 调用会话服务
            GatewaySession gatewaySession = gatewaySessionFactory.openSession(uri);
            IGenericReference re = gatewaySession.getMapper();
            SessionResult sessionResult = re.$invoke(args);

            // 封装返回结果
            DefaultFullHttpResponse response = new ResponseParser().parse("0000".equals(sessionResult.getCode()) ? GatewayResultMessage.buildSuccess(sessionResult.getData()).setNode(node()): GatewayResultMessage.buildError(AgreementConstants.ResponseCode._404.getCode(), "网关协议调用失败！").setNode(node()));
            channel.writeAndFlush(response);
        } catch (Exception e) {
            // 封装返回结果
            DefaultFullHttpResponse response = new ResponseParser().parse(GatewayResultMessage.buildError(AgreementConstants.ResponseCode._502.getCode(), "网关协议调用失败！" + e.getMessage()).setNode(node()));
            channel.writeAndFlush(response);
         }
    }

    private String node(){
        return gatewaySessionFactory.getConfiguration().getHostName() + ":" + gatewaySessionFactory.getConfiguration().getPort();
    }
}
