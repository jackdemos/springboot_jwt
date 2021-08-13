package com.taocaicai.jwt.entity;

import lombok.Data;

/**
 * @project spring-redis
 * @author Oakley
 * @created 2021-08-11 21:43:21:43
 * @package org.taocaicai.entity
 * @description TODO
 * @version: 0.0.0.1
 */
@Data
public class User {
  private String id;
  private String username;
  private String password;

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }
}
