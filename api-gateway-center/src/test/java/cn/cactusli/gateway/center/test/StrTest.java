package cn.cactusli.gateway.center.test;

import cn.cactusli.gateway.center.domain.docker.model.vo.LocationVO;
import cn.cactusli.gateway.center.domain.docker.model.vo.UpstreamVO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Package: cn.cactusli.gateway.center.test
 * Description:
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/10/7 11:29
 * @Github https://github.com/lixuanfengs
 */
public class StrTest {

    public static void main(String[] args) {

        List<UpstreamVO> upstreamList = new ArrayList<>();
        upstreamList.add(new UpstreamVO("api01", "least_conn;", Arrays.asList("192.168.1.102:9001;", "192.168.1.102:9002;")));
        upstreamList.add(new UpstreamVO("api02", "least_conn;", Arrays.asList("192.168.1.102:9003;")));

        List<LocationVO> locationList = new ArrayList<>();
        locationList.add(new LocationVO("/api01/", "http://api01;"));
        locationList.add(new LocationVO("/api02/", "http://api02;"));

        String config = "\n" +
                "user  nginx;\n" +
                "worker_processes  auto;\n" +
                "\n" +
                "error_log  /var/log/nginx/error.log notice;\n" +
                "pid        /var/run/nginx.pid;\n" +
                "\n" +
                "\n" +
                "events {\n" +
                "    worker_connections  1024;\n" +
                "}\n" +
                "\n" +
                "\n" +
                "http {\n" +
                "    include       /etc/nginx/mime.types;\n" +
                "    default_type  application/octet-stream;\n" +
                "\n" +
                "    log_format  main  '$remote_addr - $remote_user [$time_local] \"$request\" '\n" +
                "                      '$status $body_bytes_sent \"$http_referer\" '\n" +
                "                      '\"$http_user_agent\" \"$http_x_forwarded_for\"';\n" +
                "\n" +
                "    access_log  /var/log/nginx/access.log  main;\n" +
                "\n" +
                "    sendfile        on;\n" +
                "    #tcp_nopush     on;\n" +
                "\n" +
                "    keepalive_timeout  65;\n" +
                "\n" +
                "    #gzip  on;\n" +
                "\n" +
                "    include /etc/nginx/conf.d/*.conf;\n" +
                "\n" +
                "    # 设定负载均衡的服务器列表\n" +
                "upstream_config_placeholder" +
                "\n" +
                "    # HTTP服务器\n" +
                "    server {\n" +
                "        # 监听80端口，用于HTTP协议\n" +
                "        listen  80;\n" +
                "\n" +
                "        # 定义使用IP/域名访问\n" +
                "        server_name 192.168.1.102;\n" +
                "\n" +
                "        # 首页\n" +
                "        index index.html;\n" +
                "\n" +
                "        # 反向代理的路径（upstream绑定），location 后面设置映射的路径\n" +
                "        location / {\n" +
                "            proxy_pass http://192.168.1.102:9001;\n" +
                "        }\n" +
                "\n" +
                "location_config_placeholder" +
                "    }\n" +
                "}\n";

        // 组装配置 Upstream
        StringBuilder upstreamStr = new StringBuilder();
        for (UpstreamVO upstream : upstreamList) {
            upstreamStr.append("\t").append("upstream").append(" ").append(upstream.getName()).append(" {\r\n");
            upstreamStr.append("\t").append("\t").append(upstream.getStrategy()).append("\r\n").append("\r\n");
            List<String> servers = upstream.getServers();
            for (String server : servers) {
                upstreamStr.append("\t").append("\t").append("server").append(" ").append(server).append("\r\n");
            }
            upstreamStr.append("\t").append("}").append("\r\n").append("\r\n");
        }

        // 组装配置 Location
        StringBuilder locationStr = new StringBuilder();
        for (LocationVO location : locationList) {
            locationStr.append("\t").append("\t").append("location").append(" ").append(location.getName()).append(" {\r\n");
            locationStr.append("\t").append("\t").append("\t").append("proxy_pass").append(" ").append(location.getProxy_pass()).append("\r\n");
            locationStr.append("\t").append("\t").append("}").append("\r\n").append("\r\n");
        }

        // 替换配置
        config = config.replace("upstream_config_placeholder", upstreamStr.toString());
        config = config.replace("location_config_placeholder", locationStr.toString());

        System.out.println(config);

    }

}
