package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bean.AuthenticationRequest;
import com.bean.AuthenticationResponse;
import com.bean.Login;
import com.jwt.JwtUtil;
import com.service.CustomUserDetailsService;


@RestController
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;
	
//====================================================================================================================================

	//  Login Operation: 
	//  http://localhost:8080/authenticate (to create token)
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	// public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {   // OR
	public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		}
		catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		
		UserDetails userdetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		String token = jwtUtil.generateToken(userdetails);
		
		// return ResponseEntity.ok(new AuthenticationResponse(token));  // OR
		return new AuthenticationResponse(token);
	}
	
//====================================================================================================================================
	
	//  Register Operation:
	//  http://localhost:8080/register : username,password and role (role_user/role_admin) 
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public Login saveUser(@RequestBody Login login) throws Exception {
		return userDetailsService.save(login);
	}
	
	//  OR
	
//	@RequestMapping(value = "/register", method = RequestMethod.POST)
//	public ResponseEntity<?> saveUser(@RequestBody Login login) throws Exception {
//		return ResponseEntity.ok(userDetailsService.save(login));
//	}
	
//====================================================================================================================================
}
