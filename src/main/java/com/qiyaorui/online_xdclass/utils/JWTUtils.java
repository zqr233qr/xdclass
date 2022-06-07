package com.qiyaorui.online_xdclass.utils;

import com.qiyaorui.online_xdclass.model.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * JWT 工具类
 * 注意点：
 * 1.生成的token可以被base64解密成明文
 * 2.base64解密出来的信息再进行修改，是会解密失败的
 * 3.无法作废以颁布的token，除非修改秘钥
 */
public class JWTUtils {
    /**
     * 到期时间 一周
     */
    private static final long EXPIRE = 60_000 * 60 * 24 * 7;

    /**
     * 秘钥
     */
    private static final String SECRET = "qiyaorui.com";

    /**
     * 令牌前缀
     */
    private static final String TOKEN_PREFIX = "xdclass";

    /**
     * 项目名
     */
    private static final String SUBJECT = "xdclass";


    /**
     * 根据用户信息 生成令牌
     * @param user
     * @return
     */
    public static String geneJsonWebToken(User user){
        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("head_img", user.getHeadImg())
                .claim("id", user.getId())
                .claim("name", user.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256, SECRET)//HS256加密
                .compact();
        token = TOKEN_PREFIX + token;
        return token;
    }

    /**
     * 校验token
     * @param token
     * @return
     */
    public static Claims checkJWT(String token){
        try{
            final Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX,"")).getBody();
            return claims;
        }catch (Exception e){
            return null;
        }
    }

}
