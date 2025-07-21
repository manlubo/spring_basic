package com.gitbaby.spring_basic.repository;

import com.gitbaby.spring_basic.entity.Memo;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@SpringBootTest
public class MemoRepositoryTest {
  @Autowired
  private MemoRepository memoRepository;
  @Autowired
  private EntityManager entityManager;


  @Test
  public void testExist(){
    log.info("{}", memoRepository);
  }

  @Test
  public void testEntityManager(){
    log.info("{}", entityManager);

    entityManager.persist(new Memo());
  }

  @Test
  @Transactional
  @Rollback(false)
  public void testEntityManager2(){
//    Memo memo = memoRepository.findById(5L).orElseThrow(RuntimeException::new);
//    memo.setMemoText("Hello World");
    // JPA dirty checking을 통해 값 변경 감지
    Memo memo = entityManager.find(Memo.class, 5L); // 영속 개체
    memo.setMemoText("Hello World");
  }

  @Test
  @Transactional
  @Rollback(false)
  public void testEntityManager3(){
    Memo memo = new Memo();
    memo.setMno(5L);
    memo.setMemoText("비영속");

    entityManager.merge(memo);
  }




  @Test
  public void testInsert(){
    Memo memo = Memo.builder().memoText("하이하이").build();
    memoRepository.save(memo);
  }

  @Test
  public void testFindById(){
    Memo memo = memoRepository.findById(4L).orElseThrow(() -> new RuntimeException("존재하지 않는 메모 번호입니다."));
    log.info("{}", memo);
  }

  @Test
  public void testFindAll() {
    memoRepository.findAll().forEach(m -> log.info("{}", m));
  }

  @Test
  @Transactional
  public void testUpdate(){
    Memo memo = memoRepository.findById(4L).orElseThrow(() -> new RuntimeException("존재하지 않는 메모입니다."));
    memo.setMemoText("수정되었습니다.");
  }

}
