package cn.cactusli.gateway.core.authorization;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Package: cn.cactusli.gateway.authorization
 * Description:
 *  JWT（JSON Web Tokens）https://jwt.io/
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/6/14 15:43
 * @Github https://github.com/lixuanfengs
 */
public class JwtUtil {

    private static final String signingKey = "B*B^5Fe";

    /**
     * 生成 JWT Token 字符串
     *
     * @param issuer    签发人
     * @param ttlMillis 有效期
     * @param claims    额外信息
     * @return Token
     */
    public static String encode(String issuer, long ttlMillis, Map<String, Object> claims) {
        if (null == claims) {
            claims = new HashMap<>();
        }

        // 签发时间（iat）：荷载部分的标准字段之一
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // 签发操作
        JwtBuilder builder = Jwts.builder()
                // 荷载部分
                .setClaims(claims)
                // 签发时间
                .setIssuedAt(now)
                // 签发人；类似 userId、userName
                .setSubject(issuer)
                // 设置生成签名的算法和秘钥
                .signWith(SignatureAlgorithm.HS256, signingKey);

        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            // 过期时间（exp）：荷载部分的标准字段之一，代表这个 JWT 的有效期。
            builder.setExpiration(exp);
        }

        return builder.compact();
    }

    public static Claims decode(String token) {
        return Jwts.parser()
                // 设置签名的秘钥
                .setSigningKey(signingKey)
                // 设置需要解析的 jwt
                .parseClaimsJws(token)
                .getBody();
    }
}
