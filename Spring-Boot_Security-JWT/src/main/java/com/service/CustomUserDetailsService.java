package com.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bean.Login;
import com.repository.LoginRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	
	@Autowired
	private LoginRepository loginRepo;

	@Autowired
	private PasswordEncoder bcryptEncoder;
	
//=============================================================================================================
	
	//  Login Operation:
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<SimpleGrantedAuthority> roles = null;	
		Login user = loginRepo.findByUsername(username);
		
		if (user != null) {
			roles = Arrays.asList(new SimpleGrantedAuthority(user.getRole()));
			return new User(user.getUsername(), user.getPassword(), roles);
		}
		throw new UsernameNotFoundException("User not found with the name " + username);
	}
	
//=============================================================================================================
	
	//  Register Operation: 
	
	public Login save(Login login) {
		login.setPassword(bcryptEncoder.encode(login.getPassword()));
		return loginRepo.save(login);
	}
	
//=============================================================================================================


}
