package com.gitbaby.spring_basic.repository;

import com.gitbaby.spring_basic.domain.dto.MemoDto;
import com.gitbaby.spring_basic.entity.Memo;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
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
  @DisplayName("등록 테스트")
  public void testInsert(){

    for(int i = 1; i <= 100; i++){
      memoRepository.save(Memo.builder().memoText(i + "번 메모입니다.").build());
    }
  }


  @Test
  @DisplayName("단일 선택 테스트")
  public void testFindById(){
    Memo memo = memoRepository.findById(7L).orElseThrow(() -> new RuntimeException("존재하지 않는 메모 번호입니다."));
    log.info("{}", memo);
  }

  @Test
  @DisplayName("리스트 테스트")
  public void testFindAll() {
    memoRepository.findAll().forEach(m -> log.info("{}", m));
  }

  @Test
  @Transactional
  @Rollback(false)
  @DisplayName("수정 테스트")
  public void testUpdate(){
    Memo memo = memoRepository.findById(7L).orElseThrow(() -> new RuntimeException("존재하지 않는 메모입니다."));
    memo.setMemoText("수정되었습니다.");
  }

  @Test
  @Rollback(true)
  @DisplayName("삭제 테스트")
  public void testDelete(){
    memoRepository.deleteById(6L);
  }

  @Test
  @DisplayName("기본 페이징 처리")
  public void testPageDefault() {
    PageRequest pageRequest = PageRequest.of(2, 10);
    Page<Memo> result = memoRepository.findAll(pageRequest);
    result.forEach( m -> log.info("{}", m));

  }

  @Test
  @DisplayName("페이지 내부 항목")
  public void testPageDefault2() {
    PageRequest pageRequest = PageRequest.of(0, 10);
    Page<Memo> result = memoRepository.findAll(pageRequest);

    log.info("전체 개수: {}", result.getTotalElements());
    log.info("전체 페이지: {}", result.getTotalPages());
    log.info("현재 페이지 번호: {}", result.getNumber());
    log.info("페이지당 개수: {}", result.getSize());
    log.info("다음 페이지 존재여부: {}", result.hasNext());
    log.info("이전 페이지 존재여부: {}", result.hasPrevious());
    log.info("첫번째 페이지인지: {}", result.isFirst());

    List<Memo> memos = result.getContent();
    memos.forEach(m -> log.info("{}", m));
  }

  @Test
  @DisplayName("jpa 검색쿼리 테스트1")
  public void testPageDefault3() {
    memoRepository.findByMnoBetweenOrderByMnoDesc(70L, 80L).forEach(m -> log.info("{}", m));
  }

  @Test
  @DisplayName("jpa 검색쿼리 테스트2")
  public void testPageDefault4() {
    Page<Memo> memos = memoRepository.findByMnoBetween(70L, 80L, PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "mno")));
    memos.forEach(m -> log.info("{}", m));
  }

  @Test
  @DisplayName("jpa 검색쿼리 테스트3 - 카운트")
  public void testPageDefault5() {
    Long count = memoRepository.count();
    log.info("count: {}", count);
  }

  @Test
  @DisplayName("Mno 지정 이거나 memotext가 특정 문자열일때의 쿼리 메소드!")
  public void testPageDefault6() {
    List<Memo> memos = memoRepository.findByMnoOrMemoText(7L,"안녕하세요");
    memos.forEach(m -> log.info("{}", m));
  }

  @Test
  @DisplayName("JPQL 테스트")
  public void testPageJpql1() {
    List<Memo> memos = memoRepository.getListDesc();
    memos.forEach(m -> log.info("{}", m));
  }
  @Test
  @DisplayName("JPQL 테스트2")
  public void testPageJpql2() {
    List<Memo> memos = memoRepository.getListDesc2();
    memos.forEach(m -> log.info("{}", m));
  }
  @Test
  @DisplayName("JPQL 테스트3")
  public void testPageJpql3() {
    int result = memoRepository.updateMemoText(7L, "수정되었습니다1.");
    log.info("result: {}", result);
  }

  @Test
  @DisplayName("JPQL 테스트4")
  public void testPageJpql4() {
   int result = memoRepository.updateMemoText2(Memo.builder().mno(7L).memoText("수정되었습니다2.").build());
   log.info("result: {}", result);
  }
  @Test
  @DisplayName("JPQL 테스트5")
  public void testPageJpql5() {
   int result = memoRepository.updateMemoText3(7L, "수정되었습니다3.");
   log.info("result: {}", result);
  }

  @Test
  @DisplayName("페이지 정렬 테스트")
  public void testPageSort(){
    Sort sort = Sort.by(Sort.Direction.DESC, "mno");
    PageRequest pageRequest = PageRequest.of(0, 5, sort);
    Page<Memo> result = memoRepository.findAll(pageRequest);
    result.forEach( m -> log.info("{}", m));

    // EAGER, LAZY
  }

  @Test
  @DisplayName("JPQL 페이징 쿼리 테스트 1")
  public void testListWithQueryObject() {
    Page<Object []> objects = memoRepository.getListWithQueryObject(0L, PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "mno")));
    objects.forEach(r -> {
      for (Object o : r){
        log.info("{}", o);
      }
    });
  }

  @Test
  @DisplayName("JPQL 페이징 쿼리 테스트 2")
  public void testListWithQueryProjection() {
    Page<MemoDto> memoDtos = memoRepository.getListWithQueryProjection(0L, PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "mno")));
    memoDtos.forEach(m -> log.info("mno {} :: memoText {} :: n {}", m.getMno(), m.getMemoText(), m.getN()));
  }


}
