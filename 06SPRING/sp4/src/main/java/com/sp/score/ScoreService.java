package com.sp.score;

import java.util.List;
import java.util.Map;

public interface ScoreService {
	public void insertScore(Score dto) throws Exception;
	public int dataCount(Map<String, Object> map);
	public List<Score> listScore(Map<String, Object> map);
	public List<Score> listScore();
	public Score readScore(String hak);
	public void updateScore(Score dto) throws Exception;
	public void deleteScore(String hak) throws Exception;
}
