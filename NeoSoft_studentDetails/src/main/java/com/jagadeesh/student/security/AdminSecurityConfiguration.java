package com.jagadeesh.student.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;

@SuppressWarnings("deprecation")
@Configuration
public class AdminSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	
	@SuppressWarnings("deprecation")
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	

	@Override
	@Bean
	public UserDetailsService userDetailsServiceBean() throws Exception {
		return super.userDetailsServiceBean();
	}

	
	private DigestAuthenticationEntryPoint getDigestEntryPoint() {
		DigestAuthenticationEntryPoint digestEntryPoint= new DigestAuthenticationEntryPoint();
		
		digestEntryPoint.setRealmName("admin-digest-realm");
		//digestEntryPoint.setKey("somedigestKey");
		digestEntryPoint.setKey("digestKey");
		return digestEntryPoint;
		
	
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("jaga").password(passwordEncoder().encode("jaga")).roles("student")
		.and()
		.withUser("sai").password(passwordEncoder().encode("sai")).roles("admin");
	
	
	}
	
	private DigestAuthenticationFilter getDigestAuthFilter() throws Exception {
		DigestAuthenticationFilter digestAuthFilter= new DigestAuthenticationFilter();
		
		digestAuthFilter.setUserDetailsService(userDetailsServiceBean());
		digestAuthFilter.setAuthenticationEntryPoint(getDigestEntryPoint());
		return digestAuthFilter;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

	http.cors().and().csrf().disable()
	.antMatcher("/student/**").addFilter(getDigestAuthFilter()).exceptionHandling()
	.authenticationEntryPoint(getDigestEntryPoint())
	.and().authorizeRequests().antMatchers("**/student/**").hasRole("admin")
	.and().authorizeRequests().antMatchers("**/student/save").hasRole("student");
	
	
	
	
	}
	
}
