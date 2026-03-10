package com.quick.web.controller;


import com.quick.web.dto.request.LoginDto;
import com.quick.web.dto.request.RegisterDto;
import com.quick.web.dto.response.UserResponse;
import com.quick.web.service.RegisterService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping ("/auth")
public class LoginController {


    RegisterService registerService;

    LoginController(RegisterService registerService){
        this.registerService=registerService;
    }
    @GetMapping("hello")
    public String hello(){
      return "Hello";
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody RegisterDto loginDto) {
        UserResponse userResponse=registerService.login(loginDto);

        return ResponseEntity.ok(userResponse);
    }

}
