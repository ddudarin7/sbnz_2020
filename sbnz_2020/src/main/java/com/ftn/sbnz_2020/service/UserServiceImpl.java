package com.ftn.sbnz_2020.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.sbnz_2020.facts.User;
import com.ftn.sbnz_2020.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Collection<User> findAll() {
		Collection<User> users = userRepository.findAll();
		return users;
	}

	@Override
	public User findOne(Long id) {
		return userRepository.getOne(id);
	}

	@Override
	public User findOne(String username){
		return userRepository.findByUsername(username);
	}
	
	@Override
	public User create(User user){
		return userRepository.save(user);
	}

	@Override
	public User update(User user){
		return userRepository.save(user);
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);	
	}
	
	@Override
    public void deleteAll() {
		userRepository.deleteAllInBatch();
    }
}
