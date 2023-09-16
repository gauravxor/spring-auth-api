package com.rest.api.controller;
import com.rest.api.models.User;
import com.rest.api.services.UserService;
import com.rest.api.utility.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.rest.api.pojo.LoginRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class LoginController {
    private final UserService userService = new UserService();

    @GetMapping("/auth/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        String emailId = loginRequest.getEmailId();
        String password = loginRequest.getPassword();

        if(emailId.isEmpty() || password.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email ID or password cannot be empty");
        }
        User data = userService.getUserByEmailId(emailId);

        if(data == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        if(!data.getPassword().equals(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect Password");
        }

        // generating the JWT access token from utility class
        JwtUtil jwtUtil = new JwtUtil();
        String accessToken = jwtUtil.generateAccessToken(data);

        Cookie cookie = new Cookie("accessToken", accessToken);
        cookie.setHttpOnly(true); // HTTP Only Cookies
        cookie.setMaxAge(3600); // 🍪 expires in an hr
        cookie.setPath("/"); // 🍪 cookie valid across all routes.
        response.addCookie(cookie);

        // Return a success response
        return ResponseEntity.ok().build();
    }
}