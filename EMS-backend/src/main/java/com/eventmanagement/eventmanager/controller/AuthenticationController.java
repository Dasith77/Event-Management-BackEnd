package com.eventmanagement.eventmanager.controller;

import com.eventmanagement.eventmanager.requests.AuthenticationRequest;
import com.eventmanagement.eventmanager.requests.RegisterRequest;
import com.eventmanagement.eventmanager.rersponses.AuthenticationResponse;
import com.eventmanagement.eventmanager.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello from secured endpoint");
    }

}
