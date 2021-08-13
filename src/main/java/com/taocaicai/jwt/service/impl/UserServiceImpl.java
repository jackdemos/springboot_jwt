package com.taocaicai.jwt.service.impl;

import com.taocaicai.jwt.entity.User;
import com.taocaicai.jwt.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @project spring-redis
 * @author Oakley
 * @created 2021-08-11 22:17:22:17
 * @package org.taocaicai.service.impl
 * @description TODO
 * @version: 0.0.0.1
 */
@Service
public class UserServiceImpl implements UserService {
  private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
  @Override
  public boolean login(User user) {
    logger.info("..........login method.........");
    String username = user.getUsername();
    String password = user.getPassword();
    if (username.equals("zhangsan") && password.equals("zhangsan")) {
      return true;
    }
    return false;
  }

  @Override
  public List<User> getList() {

    User user1 = new User("Jack", "jack");
    User user2 = new User("lisi", "12345");
    User user3 = new User("luce", "lu1234");
    List<User> list = new ArrayList<>();
    list.add(user1);
    list.add(user2);
    list.add(user3);
    return list;
  }
}
