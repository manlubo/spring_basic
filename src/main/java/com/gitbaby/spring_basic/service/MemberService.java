package com.gitbaby.spring_basic.service;

import com.gitbaby.spring_basic.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface MemberService {
  void register(Member member);

}
