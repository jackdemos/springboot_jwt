package com.taocaicai.jwt.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.taocaicai.jwt.entity.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

/**
 * @project spring-redis
 * @author Oakley
 * @created 2021-08-11 21:42:21:42
 * @package org.taocaicai.util
 * @description TODO
 * @version: 0.0.0.1
 */
public class TokenUtil {

  private static Logger logger = LoggerFactory.getLogger(TokenUtil.class);
  /** 过期时间有效期 2 小时 */
  private static final long EXPIRE_TIME = 2 * 60 * 1000;
  /** 密钥盐 */
  private static final String TOKEN_SECRET = "6Dx8SIuaHXJYnpsG18SSpjPs50lZcT52";

  /**
   * @description: 生成Toekn
   * @param: User
   * @return: String
   * @author: Oakley
   * @since: 1.0 created: 2021-08-12 7:46
   */
  public static String sign(User user) {
    Date expiresAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
    String sign =
        JWT.create()
            .withIssuer("auth0")
            .withClaim("username", user.getUsername())
            .withExpiresAt(expiresAt)
            .sign(Algorithm.HMAC256(TOKEN_SECRET));
    return sign;
  }

  /**
   * @description: 验证token
   * @param: token
   * @return: boolean
   * @author: Oakley
   * @since: 1.0 created: 2021-08-12 7:46
   */
  public static Boolean verify(String token) throws JWTVerificationException {
    JWTVerifier jwtVerifier =
        JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
    DecodedJWT jwt = jwtVerifier.verify(token);

    logger.info("认证通过：");
    logger.info("issuer: " + jwt.getIssuer());
    logger.info("username: " + jwt.getClaim("username").asString());
    logger.info("过期时间：      " + jwt.getExpiresAt());
    return true;
  }

  /**
   * @description:
   * @author: Oakley
   * @date: 2021-08-13 9:45
   * @param: token
   * @Return:
   * @Exception:
   * @since: 1.0
   */
  public static Boolean checkToken(String token) {
    if (token == null) {
      return false;
    }
    JwtParser parser = Jwts.parser();
    try {
      Jws<Claims> claimsJws = parser.setSigningKey(TOKEN_SECRET).parseClaimsJws(token);
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * @description: 验证
   * @param: args
   * @return: void
   * @author: Oakley
   * @since: 1.0 created: 2021-08-12 7:45
   */
  public static void main(String[] args) {
    User user = new User("liuliu", "liuliu");
    String token = sign(user);
    verify(token);
  }
}
