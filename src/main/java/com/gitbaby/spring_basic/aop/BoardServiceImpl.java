package com.gitbaby.spring_basic.aop;

import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {
  @Override
  public void write(String title, String content) {
    System.out.println(title + ", " + content);
    System.out.println("글쓰기 완료");
  }

  @Override
  public Object get(Long bno) {

    System.out.println(bno + "번 글 조회 완료");
    return bno;
  }

  @Override
  public void modify(String title, String content) {
    System.out.println(title + ", " + content);
    System.out.println("글수정 완료");
  }

  @Override
  public void remove(Long bno)  {
    if(bno == 1L){
      throw new RuntimeException(bno + "번 게시글 안받음 ㅋㅋ");
    } else {
      System.out.println(bno + " 번 글을 삭제 완료");
    }
  }
}
