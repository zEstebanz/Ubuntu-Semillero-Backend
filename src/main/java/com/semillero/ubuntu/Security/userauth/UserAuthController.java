package com.semillero.ubuntu.Security.userauth;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserAuthController {

    private final UserAuthService userAuthService;
    @PostMapping("/token")
    public ResponseEntity<String> authUser(@RequestHeader("Authorization") String authHeader, HttpServletResponse response){
        response.setHeader("Authorization", userAuthService.authUser(authHeader));
        return ResponseEntity.ok("Success");
    }

    @GetMapping("/user/details")
    public ResponseEntity<String> getUserInfo(@RequestHeader("Authorization") String authHeader, HttpServletResponse response) {
        response.setHeader("Authorization",userAuthService.getUserInfo(authHeader) );
        return ResponseEntity.ok("Success");
    }
}

