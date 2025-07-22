package com.gitbaby.spring_basic.repository;

import com.gitbaby.spring_basic.entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {
  List<Memo> findByMnoBetweenOrderByMnoDesc(Long mno1, Long mno2);

  Page<Memo> findByMnoBetween(Long mno1, Long mno2, Pageable pageable);

  Long countByMno(Long mno);


  List<Memo> findByMnoOrMemoText(Long mno, String memoText);

  @Query("select m from Memo m order by m.mno desc limit 10")
  List<Memo> getListDesc();

  @Query(value = "select * from tbl_memo order by mno desc limit 10", nativeQuery = true)
  List<Memo> getListDesc2();

}
