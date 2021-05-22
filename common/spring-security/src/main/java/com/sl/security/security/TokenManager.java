package com.sl.security.security;

import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author shuliangzhao
 * @Title: TokenManager
 * @ProjectName acl-parent
 * @Description: TODO
 * @date 2021/5/22 20:58
 */
@Component
public class TokenManager {

    //有效期
    private long tokenEcpiration = 24*60*60*1000;

    //编码秘钥
    private String tokenSignkey = "12345";

    //使用用户名根据jwt生成token
    public String createToken(String username) {
        String token = Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis()+tokenEcpiration))
                .signWith(SignatureAlgorithm.HS512,tokenSignkey).compressWith(CompressionCodecs.GZIP)
                .compact();
        return token;
    }

    //根据token得到用户信息
    public String getUserInfoFromToken(String token) {
        String userInfo = Jwts.parser().setSigningKey(tokenSignkey).parseClaimsJws(token).getBody().getSubject();
        return userInfo;
    }

    public void removeToken(String token) {}
}
