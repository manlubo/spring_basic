package com.gitbaby.spring_basic.service;

import com.gitbaby.spring_basic.aop.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardServiceTest {
  @Autowired
  BoardService boardService;


}
