package com.jagadeesh.student.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailService  implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//name have to write
		if("name".equals(username)) {
			System.out.println("hello User" );
			return new User("name","EncodedPassword",new ArrayList<>());
		}
		else {
			throw new UsernameNotFoundException("USer Not found with username : "+username);
		}
	}

}
