package com.ftn.sbnz_2020.service;

import java.util.Collection;

import com.ftn.sbnz_2020.facts.User;

public interface UserService {

	Collection<User> findAll();
	
	User findOne(Long id);
	
	User findOne(String username);
	
	User create(User user);
	
	User update(User user);
	
	void delete(Long id);
	
	void deleteAll();
}
