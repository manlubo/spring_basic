package com.gitbaby.spring_basic.domain;

import com.gitbaby.spring_basic.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@Slf4j
//@ContextConfiguration(locations = "classpath:xml/bean-config.xml")
//@Runwith(Junit5.class) - 이전에 테스트하는방법
@ContextConfiguration(classes = AppConfig.class)
public class MemberTest {
  @Autowired
  Member member;

  @Test
  @DisplayName("정상 작동 여부")
  public void testExist(){
    log.info("{}", member);
  }



}
