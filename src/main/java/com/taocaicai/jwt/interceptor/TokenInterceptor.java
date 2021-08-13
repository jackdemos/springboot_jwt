package com.taocaicai.jwt.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.taocaicai.jwt.util.TokenUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @project spring-redis
 * @author Oakley
 * @created 2021-08-11 22:09:22:09
 * @package org.taocaicai.interceptor
 * @description TODO
 * @version: 0.0.0.1
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {
  private static Logger logger = LoggerFactory.getLogger(TokenUtil.class);
  private static String DEFAULT_METHOD_NAME ="OPTIONS";
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws IOException {
    logger.info(".........TokenInterceptor preHandle ..........");
    if (DEFAULT_METHOD_NAME.equals(request.getMethod())) {
      response.setStatus(HttpServletResponse.SC_OK);
      return true;
    }
    response.setCharacterEncoding("UTF-8");
    response.setContentType("application/json; charset=utf-8");
    PrintWriter out = null;
    String token = request.getHeader("admin-token");
    if (token != null) {
      try {
        boolean result = TokenUtil.verify(token);
        if (result) {
         logger.info("通过拦截器");
          return true;
        }
      } catch (Exception e) {
        JSONObject json = new JSONObject();
        if (e instanceof TokenExpiredException) {
          json.put("success", 0);
          json.put("code", 5000);
          json.put("msg", "token已过期");
          response.getWriter().append(json.toJSONString());
          return false;
        }
        logger.warn("认证失败，未通过拦截器");
      }
    }
    return false;
  }
}
