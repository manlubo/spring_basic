package com.gitbaby.spring_basic.aop.pointcut;

import com.gitbaby.spring_basic.service.FirstService;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class MyFirstTwoPointcut extends StaticMethodMatcherPointcut {

  @Override
  public boolean matches(Method method, Class<?> targetClass) {
    // 파라미터의 개수, 반환타입 등으로 조건 설정가능

    // firstService의 two에만 적용

    return method.getName().equals("two") && targetClass == FirstService.class;
  }
}
