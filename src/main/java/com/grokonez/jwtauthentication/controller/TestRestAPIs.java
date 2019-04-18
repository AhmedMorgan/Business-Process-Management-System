package com.grokonez.jwtauthentication.controller;

import com.grokonez.jwtauthentication.message.response.GeneralResponseObject;
import com.grokonez.jwtauthentication.message.response.ResponseObject;
import com.grokonez.jwtauthentication.model.Request;
import com.grokonez.jwtauthentication.security.jwt.JwtProvider;
import com.grokonez.jwtauthentication.service.RequestService;
import com.grokonez.jwtauthentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TestRestAPIs {
	@Autowired
	RequestService requestService;
	@Autowired
	UserService userService;
	@Autowired
	private JwtProvider tokenProvider;

	@GetMapping("/api/test/user")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public String userAccess() {
		return ">>> User Contents!";
	}

	@GetMapping("/api/test/pm")
	@PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
	public String projectManagementAccess() {
		return ">>> Board Management Project";
	}
	
	@GetMapping("/api/test/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return ">>> Admin Contents";
	}

	@PostMapping("/api/test/createrequest")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseObject createRequest(@RequestBody Request request){return requestService.createRequest(request); }

	@GetMapping("/api/test/allusers")
	public GeneralResponseObject getAllUsers(){ return userService.getAllUsers(); }

	@GetMapping("/api/test/profile/{username}")
	public GeneralResponseObject getProfile(String userName){ return userService.getProfile(userName); }

	@PutMapping("/api/test/reject/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseObject refuseRequest(@PathVariable int id){
		return requestService.refuseRequest(id);
	}
}