package com.taocaicai.jwt.service;

import com.taocaicai.jwt.entity.User;

import java.util.List;

/**
 * @project spring-redis
 * @author Oakley
 * @created 2021-08-11 22:16:22:16
 * @package org.taocaicai.service
 * @description TODO
 * @version: 0.0.0.1
 */
public interface UserService {

    public boolean login(User user);

    public List<User> getList();
}
