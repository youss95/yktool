package com.ksy.syserver.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ksy.syserver.domain.User;
import com.ksy.syserver.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		
		if(user == null) {
			new UsernameNotFoundException("찾을 수 업는 유저");
		}
		return user;
	}
	
	@Transactional
	public User loadUserById(Long id) {
		User user = userRepository.getById(id);
		if(user==null) {
			new UsernameNotFoundException("유저를 찾을수 없습니다.");
		}
		return user;
	}
	
	
}
