package com.abhishek.springsecurity.controller;

import com.abhishek.springsecurity.dto.AuthenticationResponse;
import com.abhishek.springsecurity.dto.MyUserDetails;
import com.abhishek.springsecurity.dto.User;
import com.abhishek.springsecurity.service.impl.JwtUserDetailsService;
import com.abhishek.springsecurity.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class JwtController {

    private final AuthenticationManager authenticationManager;
    private final JwtUserDetailsService userDetailsService;
    private final JwtUtil jwtTokenUtil;

    public JwtController(
            AuthenticationManager authenticationManager,
            JwtUserDetailsService userDetailsService,
            JwtUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody MyUserDetails myUserDetails) throws Exception {

        try {
            log.info("api = /authenticate, method = POST, result = IN_PROGRESS");
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(myUserDetails.getUsername(),
                            myUserDetails.getPassword()));

            final User userDetails = userDetailsService
                    .loadUserByUsername(myUserDetails.getUsername());

            final String jwt = jwtTokenUtil.generateToken(userDetails);

            log.info("api = /authenticate, method = POST, result = SUCCESS");
            return ResponseEntity.ok(new AuthenticationResponse(jwt));

        } catch (BadCredentialsException e) {
            log.info("api = /authenticate, method = POST, result = ERROR", e);
            throw new Exception("Incorrect user name / password", e);
        }
    }
}