package com.servicer.app.authentication;

import com.servicer.app.Dto.LoginRequest;
import com.servicer.app.Dto.SignupDto;
import com.servicer.app.authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController

@RequestMapping("/api/v1/auth/")
@CrossOrigin("http://localhost:4200/register")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupDto signupDto) {
        authenticationService.signup(signupDto);

        return new ResponseEntity<>("User Registration successful", HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<String> signup(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        authenticationService.login(loginRequest,response);
        return new ResponseEntity<>("Login successful", HttpStatus.OK);
    }
}
