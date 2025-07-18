package com.gitbaby.spring_basic.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
//@Component
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
//  @Value("Winter")
  private String name;
//  @Value("32")
  private int age;
  private String id;

  public Member(String name, int age) {
    this.name = name;
    this.age = age;
  }

}
