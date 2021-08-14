package com.taocaicai.jwt.controller;

import com.taocaicai.jwt.entity.User;
import com.taocaicai.jwt.service.UserService;
import com.taocaicai.jwt.util.TokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @project spring-redis
 * @author Oakley
 * @created 2021-08-11 22:15:22:15
 * @package org.taocaicai.controller
 * @description
 * @version: 0.0.0.1
 */
@RestController
@CrossOrigin("*")
public class UserController {

  @Autowired private UserService userService;

  @PostMapping(value = "/login")
  @ResponseBody
  public Map<String, Object> login(String username, String password) {

    Map<String, Object> map = new HashMap<>(16);
    User user = new User(username, password);

    if (userService.login(user)) {
      String token = TokenUtil.sign(user);
      if (token != null) {
        map.put("code", "10000");
        map.put("message", "认证成功");
        map.put("token", token);
        return map;
      }
    }

    map.put("code", "0000");
    map.put("message", "认证失败");
    return map;
  }

  @PostMapping(value = "/getList")
  public List<User> getList() {

    List userList = userService.getList();
    return userList;
  }

  @PostMapping("/checkToken")
  public boolean checkToken(HttpServletRequest request) {
    String token = request.getHeader("token");
    Boolean result = TokenUtil.checkToken(token);
    return result;
  }
}
