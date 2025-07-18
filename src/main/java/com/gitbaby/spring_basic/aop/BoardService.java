package com.gitbaby.spring_basic.aop;

public interface BoardService {
  void write(String title, String content);
  Object get(Long bno);
  void modify(String title, String content);
  void remove(Long bno);
}
