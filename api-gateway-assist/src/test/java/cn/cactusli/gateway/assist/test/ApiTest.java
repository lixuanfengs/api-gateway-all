package cn.cactusli.gateway.assist.test;

import cn.cactusli.gateway.assist.common.Result;
import cn.cactusli.gateway.assist.domain.model.aggregates.ApplicationSystemRichInfo;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Package: cn.cactusli.gateway.assist.test
 * Description:
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/8/7 11:26
 * @Github https://github.com/lixuanfengs
 */
public class ApiTest {


    @Test
    public void stse () {
        String url = "http://218.249.73.249:29000/kexie/pexels-magda-ehlers-1279813.jpg";

        String url1 = getUrl(url, (str) -> {
            int startIndex = str.indexOf("/kexie");
            return startIndex != -1 ? str.substring(startIndex) : str;
        });
        System.out.println(url1);
    }

    public String getUrl(String url, Stringpr stringpr) {
        return stringpr.process(url);
    }

    @FunctionalInterface
    interface Stringpr {
        String process(String str);
    }

    @Test
    public void test_register_gateway() {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("groupId", "10001");
        paramMap.put("gatewayId", "api-gateway-g4");
        paramMap.put("gatewayName", "电商配送网关");
        paramMap.put("gatewayAddress", "127.0.0.1");

        String resultStr = HttpUtil.post("http://localhost:8001/cactus/admin/config/registerGateway", paramMap, 350);
        System.out.println(resultStr);

        Result result = JSON.parseObject(resultStr, Result.class);
        System.out.println(result.getCode());
    }

    @Test
    public void test_pullApplicationSystemRichInfo() {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("gatewayId", "api-gateway-g4");
        String resultStr = HttpUtil.post("http://localhost:8001/cactus/admin/config/queryApplicationSystemRichInfo", paramMap, 350);
        Result<ApplicationSystemRichInfo> result = JSON.parseObject(resultStr, new TypeReference<Result<ApplicationSystemRichInfo>>(){});
        System.out.println(JSON.toJSONString(result.getData()));
    }

}
