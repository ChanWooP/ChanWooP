package com.sp.score;

import java.util.List;
import java.util.Map;

public interface ScoreService {
	public void insertScore(Score dto) throws Exception;
	public List<Score> listScore(Map<String, Object> map);
	public int dataCount(Map<String, Object> map);
	public Score readScore(String hak);
	public void updateScore(Score dto) throws Exception;
	public void deleteScore(String hak) throws Exception;
	public void deleteScoreList(List<String> haks) throws Exception;
}