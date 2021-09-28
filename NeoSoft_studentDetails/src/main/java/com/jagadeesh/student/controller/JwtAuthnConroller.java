package com.jagadeesh.student.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jagadeesh.student.jwtConfig.JwtRequestFilter;
import com.jagadeesh.student.jwtConfig.JwtUtilToken;
import com.jagadeesh.student.jwtmodel.JwtRequest;
import com.jagadeesh.student.jwtmodel.JwtResponce;

@RestController
public class JwtAuthnConroller {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtilToken jwtUtilToken;

	@Autowired
	private UserDetailsService jwtInMemoryuserdetailService;

	@PostMapping("/authenticate")
	public ResponseEntity<?> genrateAuthenticationToken(@RequestBody JwtRequest authenticationRequeset) throws Exception {
		authenticate(authenticationRequeset.getUsername(),authenticationRequeset.getPassword());
		
		UserDetails userDetails= jwtInMemoryuserdetailService.loadUserByUsername(authenticationRequeset.getUsername());
		
		String token= jwtUtilToken.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponce(token));

	}

	private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("userSisabked",e);
		}
		
	}

}
