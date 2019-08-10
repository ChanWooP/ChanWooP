package com.sp.score;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("score.scoreServiceImpl")
public class ScoreServiceImpl implements ScoreService{
	
	@Autowired
	private ScoreDAO scoreDAO;
	
	@Override
	public void insertScore(Score dto) throws Exception {
		try {
			scoreDAO.insertScore(dto);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Score> listScore(Map<String, Object> map) {
		return scoreDAO.listScore(map);
	}

	@Override
	public int dataCount(Map<String, Object> map) {
		return scoreDAO.dataCount(map);
	}

	@Override
	public Score readScore(String hak) {
		Score dto = null;
		
		try {
			dto=scoreDAO.readScore(hak);
		} catch (Exception e) {
		}
		
		return dto;
	}

	@Override
	public void updateScore(Score dto) throws Exception {
		try {
			scoreDAO.updateScore(dto);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void deleteScore(String hak) throws Exception {
		try {
			scoreDAO.deleteScore(hak);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void deleteScoreList(List<String> haks) throws Exception {
		try {
			scoreDAO.deleteScoreList(haks);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
