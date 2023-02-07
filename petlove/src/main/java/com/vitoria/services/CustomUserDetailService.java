package com.vitoria.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vitoria.exceptions.ResourceNotFoundException;
import com.vitoria.models.UserModel;
import com.vitoria.repositories.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{
	@Autowired
	private UserRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel user=this.repo.findByUsername(username)
			.orElseThrow(()-> new ResourceNotFoundException("User", "username: "+username));
		return user;	
	}

}