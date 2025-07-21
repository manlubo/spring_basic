package com.gitbaby.spring_basic.service;

import com.gitbaby.spring_basic.domain.Member;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
@ToString
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
//  @NonNull
//  private Member member;

//  public MemberServiceImpl(Member member){
//    this.member = member;
//  }

  @Override
  public void register(Member member) {

  }
}
