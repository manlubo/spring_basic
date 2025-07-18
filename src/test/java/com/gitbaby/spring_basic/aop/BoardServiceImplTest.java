package com.gitbaby.spring_basic.aop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardServiceImplTest {
  @Autowired
  BoardService boardService;

  @Test
  void write() {
    boardService.write("title", "content");
  }

  @Test
  void get() {
    boardService.get(2L);
  }

  @Test
  void modify() {
    boardService.modify("title", "content");
  }

  @Test
  void remove() {
    boardService.remove(2L);
  }
}