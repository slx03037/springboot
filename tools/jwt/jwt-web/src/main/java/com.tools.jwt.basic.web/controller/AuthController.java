package com.tools.jwt.basic.web.controller;

/**
 * @author shenlx
 * @description
 * @date 2026/5/11 10:47
 */

import com.tools.jwt.basic.web.config.JwtUtil;
import com.tools.jwt.basic.web.entity.User;
import com.tools.jwt.basic.web.model.LoginRequest;
import com.tools.jwt.basic.web.model.RegisterRequest;
import com.tools.jwt.basic.web.model.resp.ApiResponse;
import com.tools.jwt.basic.web.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
            
            private final AuthenticationManager authenticationManager;
            private final JwtUtil jwtUtil;
            private final UserRepository userRepository;
            private final PasswordEncoder passwordEncoder;
            
            @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
                try {
                    Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                        )
                    );
                    
                    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                    String token = jwtUtil.generateToken(
                        userDetails.getUsername(),
                        userDetails.getAuthorities()
                    );

                    Map<String,String> map=new HashMap<>();
                    map.put("accessToken",token);
                    map.put("username",userDetails.getUsername());
                    
                    return ResponseEntity.ok(ApiResponse.success(map));
                    
                } catch (BadCredentialsException e) {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(ApiResponse.error("用户名或密码错误"));
                }
            }
            
            @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
                if (userRepository.existsByUsername(request.getUsername())) {
                    return ResponseEntity.badRequest()
                        .body( ApiResponse.error("用户名已存在"));
                }
                
                User user = User.builder()
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .email(request.getEmail())
                    .enabled(true)
                    .accountNonLocked(true)
                    .build();
                
                // 默认赋予普通用户角色
                userRepository.save(user);
                
                return ResponseEntity.ok(ApiResponse.success("注册成功"));
            }
        }
