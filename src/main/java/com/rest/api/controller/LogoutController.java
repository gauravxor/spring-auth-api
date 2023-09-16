package com.rest.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;


@RestController
public class LogoutController {

    @GetMapping("/auth/logout")
    public ResponseEntity<String> logout(@CookieValue(name = "accessToken", required = false) String accessTokenValue, HttpServletResponse response) {
        if (accessTokenValue != null) {
            // If the accessToken exists -> create a new cookie with max age 0 to delete it
            // System.out.println("Access TOken: " + accessTokenValue);
            Cookie accessTokenCookie = new Cookie("accessToken", null);
            accessTokenCookie.setMaxAge(0);
            response.addCookie(accessTokenCookie);
            return ResponseEntity.ok("Logged out successfully.");
        } else {
            return ResponseEntity.badRequest().body("No access token cookie found. You are already logged out.");
        }
    }
}