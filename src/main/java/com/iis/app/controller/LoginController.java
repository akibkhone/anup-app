package com.iis.app.controller;

import com.iis.app.dto.User;
import com.iis.app.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {

	private final LoginService loginService;

	@Autowired
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody User user) {
		try {
			String token = loginService.authenticate(user.getUsername(), user.getPassword());
			return ResponseEntity.ok(new JwtResponse(token));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
		}
	}

	// Inner class for JWT response
	public static class JwtResponse {
		private String token;

		public JwtResponse() {
		}

		public JwtResponse(String token) {
			this.token = token;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}
	}
}
