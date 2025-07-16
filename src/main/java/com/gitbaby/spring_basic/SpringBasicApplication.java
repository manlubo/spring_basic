package com.gitbaby.spring_basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class SpringBasicApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBasicApplication.class, args);
    log.info("hello world");
  }

}
