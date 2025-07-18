package com.gitbaby.spring_basic.aop.pointcut;

import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class MySimplePointcut extends StaticMethodMatcherPointcut {

  @Override
  public boolean matches(Method method, Class<?> targetClass) {
    // 파라미터의 개수, 반환타입 등으로 조건 설정가능

    //매개변수 갯수가 1개 그리고 리턴타입이 void
    return method.getReturnType() == void.class && method.getParameterCount() == 1;
  }
}
