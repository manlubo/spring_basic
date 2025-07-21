package com.gitbaby.spring_basic.repository;

import com.gitbaby.spring_basic.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Slf4j
public class MemberRepositoryTest {
  @Autowired
  private MemberRepository memberRepository;

  @Test
  public void testExist(){
    log.info("{}", memberRepository);
  }

  @Test
  @Transactional
  @Rollback(false)
  public void testInsert(){
    Member member = Member.builder().id("qwer3").pw("1234").age(23).name("오예원").build();
    memberRepository.save(member);
  }


  @Test
  public void testFindById(){
    Member member = memberRepository.findById(1L).orElseThrow(() -> new RuntimeException("지정된 회원 번호가 없습니다."));
    log.info("{}", member);
  }

  @Test
  public void testFindAll(){
    memberRepository.findAll().forEach(m -> log.info("{}", m));
  }

  @Test
  @Transactional
  @Rollback(false)
  public void testUpdate(){
    Member member = memberRepository.findById(1L).orElseThrow(() -> new RuntimeException("지정된 회원 번호가 없습니다."));
    member.setAge(20);
  }


  @Test
  public void testDelete(){
    memberRepository.deleteById(3L);
  }
}
