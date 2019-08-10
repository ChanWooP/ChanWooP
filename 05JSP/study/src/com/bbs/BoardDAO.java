package com.bbs;

import java.util.List;

public interface BoardDAO {
   
   public int insertBoard(BoardDTO dto);
   public int dataCount();
   public int dataCount(String condition, String keyword);
   public List<BoardDTO> listBoard(int start, int end);
   public List<BoardDTO> listBoard(int start, int end, String condition, String keyword);
   public BoardDTO readBoard(int num);
   public BoardDTO preReadBoard(int num, String condition, String keyword);
   public BoardDTO nextReadBoard(int num, String condition, String keyword);
   public int updateHitCount(int num);
   public int updateBoard(BoardDTO dto);
   public int deleteBoard(int num);

}