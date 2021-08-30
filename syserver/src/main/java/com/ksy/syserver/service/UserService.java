package com.ksy.syserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ksy.syserver.domain.User;
import com.ksy.syserver.exception.UsernameExistException;
import com.ksy.syserver.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public User saveUser (User newUser) {
		try {
			newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
			
			//username should be unique(exception)
			newUser.setUsername(newUser.getUsername());
			
			//make sure that password and confirmpw match
			
			return userRepository.save(newUser);
		}catch(Exception e) {
			throw new UsernameExistException("이미 존재하는 username입니다.");
		}
		
	}
	
}
