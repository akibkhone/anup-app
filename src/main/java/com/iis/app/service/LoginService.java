package com.iis.app.service;

import com.iis.app.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final JwtUtil jwtUtil;

    // For demonstration, using hardcoded credentials.
    // In a real application, you'd fetch user details from a database.
    private final String DEMO_USERNAME = "Anup";
    private final String DEMO_PASSWORD = "Anup@2024";

    @Autowired
    public LoginService(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public String authenticate(String username, String password) throws Exception {
        if (validateCredentials(username, password)) {
            return jwtUtil.generateToken(username);
        } else {
            throw new Exception("Invalid username or password");
        }
    }

    private boolean validateCredentials(String username, String password) {
        // Replace this logic with real user authentication
        return DEMO_USERNAME.equals(username) && DEMO_PASSWORD.equals(password);
    }
}
