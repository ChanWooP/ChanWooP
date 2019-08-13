package com.sp.person;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("person.personDAO")
public class PersonDAOImpl implements PersonDAO{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insertPerson(Person dto) throws Exception {
		try {
			sqlSession.insert("person.insertPerson",dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}
}
