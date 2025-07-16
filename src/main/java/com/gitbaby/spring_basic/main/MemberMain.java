package com.gitbaby.spring_basic.main;

import com.gitbaby.spring_basic.domain.Member;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MemberMain {
  public static void main(String[] args) {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("xml/bean-config.xml");
    Member m = context.getBean("member", Member.class);
    System.out.println(m);
  }
}
