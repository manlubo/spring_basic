package com.gitbaby.spring_basic.aop;

import com.gitbaby.spring_basic.aop.advice.MyBeforeAdvice;
import com.gitbaby.spring_basic.aop.advice.MyThrowAdvice;
import com.gitbaby.spring_basic.aop.pointcut.MyFirstTwoPointcut;
import com.gitbaby.spring_basic.aop.pointcut.MySimplePointcut;
import com.gitbaby.spring_basic.service.FirstService;
import com.gitbaby.spring_basic.service.SecondService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.aop.Advice;
import org.junit.jupiter.api.Test;
import org.springframework.aop.*;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j
@SpringBootTest
public class ProxyTest {
  @Autowired
  private BoardService boardService;
  @Autowired
  private LoggingAdvice advice;
  @Autowired
  private MyBeforeAdvice before;
  @Autowired
  private AfterReturningAdvice after;
  @Autowired
  private MyThrowAdvice myThrowAdvice;
  @Autowired
  private MySimplePointcut pointcut;
  @Autowired
  private FirstService firstService;
  @Autowired
  private SecondService secondService;

  private FirstService proxy1;
  private SecondService proxy2;


  private BoardService proxy;
  @PostConstruct
  public void init() {
    Advice[] Advices = new Advice[]{after, myThrowAdvice};
    ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.setTarget(boardService);
    for(Advice a : Arrays.asList(Advices)) {
      proxyFactory.addAdvice(a);
    }
    proxy = (BoardService) proxyFactory.getProxy();
  }

  @Test
  public void testExist() {
    log.info("{}", boardService);
  }

  @Test
  public void testWrite() {
    boardService.write("원본 객체의 제목", "내용");

    ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.setTarget(boardService);
    proxyFactory.addAdvice(advice);
    BoardService proxy = (BoardService) proxyFactory.getProxy();

    proxy.write("프록시 객체의 제목", "내용");
  }

  @Test
  public void testBefore() {
    ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.setTarget(boardService);
    proxyFactory.addAdvice(advice);
    proxyFactory.addAdvice(before);
    BoardService proxy = (BoardService) proxyFactory.getProxy();

    System.out.println("=========== 글 쓰기 ===========");
    proxy.write("프록시 객체의 제목", "내용");

    System.out.println("=========== 글 조회 ===========");
    proxy.get(3L);
  }

  @Test
  public void testAfterReturning() {
    try{
    proxy.remove(1L);
    }
    catch (Exception e){
      System.out.println(e.getMessage());
    }
  }

  @Test
  public void testAdvisor() {
    ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.setTarget(boardService);

//    Advisor advisor;
    PointcutAdvisor pointcutAdvisor = new DefaultPointcutAdvisor(pointcut, before);
    proxyFactory.addAdvisor(pointcutAdvisor);

    proxy = (BoardService) proxyFactory.getProxy();

    proxy.write("제목", "내용");
    proxy.get(3L);
    proxy.remove(4L);
  }

  @Test
  public void testAdvisor2() {
    ProxyFactory firstFactory = new ProxyFactory();
    firstFactory.setTarget(firstService);
    MethodBeforeAdvice beforeAdvice = (method, args, target) -> System.out.println("밑은 first입니다.");

    PointcutAdvisor pointcutAdvisor = new DefaultPointcutAdvisor(
      new StaticMethodMatcherPointcut() {
      @Override
      public boolean matches(Method method, Class<?> targetClass) {
        return method.getName().equals("two") && targetClass == FirstService.class;
      }
    }, beforeAdvice);

    firstFactory.addAdvisor(pointcutAdvisor);
    ProxyFactory secondFactory = new ProxyFactory();
    secondFactory.setTarget(secondService);
    secondFactory.addAdvisor(pointcutAdvisor);

    proxy1 = (FirstService) firstFactory.getProxy();
    proxy2 = (SecondService) secondFactory.getProxy();

    proxy1.one();
    proxy1.two();
    proxy2.one();
    proxy2.two();
  }


  @Test
  public void testAspectJ() {
    AspectJExpressionPointcut pc = new AspectJExpressionPointcut();
    pc.setExpression("execution(* *.write*(..))");

    DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pc, before);

    ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.setTarget(boardService);

    proxyFactory.addAdvisor(advisor);

    BoardService proxy = (BoardService) proxyFactory.getProxy();
    proxy.write("title", "content");
    proxy.get(2L);
  }


}
