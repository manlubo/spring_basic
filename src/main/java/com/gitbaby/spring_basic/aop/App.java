package com.gitbaby.spring_basic.aop;

import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Proxy;

public class App {
  public static void main(String[] args) {
    BoardService target = new BoardServiceImpl();
    System.out.println("=========== target의 결과물 ===========");
    target.write("제목", "내용");

    BoardService proxy = (BoardService) Proxy.newProxyInstance(
      BoardService.class.getClassLoader(),
      new Class[] {BoardService.class},
      new LogingInvocationHandler(target)
    );

    System.out.println("=========== proxy의 결과물 ===========");
    proxy.write("tytle", "content");


  }
}
