package com.sp.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("person.personService")
public class PersonServiceImpl implements PersonService{
	@Autowired
	private PersonDAO personDAO;

	@Override
	public void insertPerson(Person dto) throws Exception {
		try {
			personDAO.insertPerson(dto);
		} catch (Exception e) {
			throw e;
		}
	}
	
	
}
