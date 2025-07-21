package com.gitbaby.spring_basic.repository;

import com.gitbaby.spring_basic.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
