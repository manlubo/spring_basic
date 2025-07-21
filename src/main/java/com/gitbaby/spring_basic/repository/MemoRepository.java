package com.gitbaby.spring_basic.repository;

import com.gitbaby.spring_basic.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository extends JpaRepository<Memo, Long> {
}
