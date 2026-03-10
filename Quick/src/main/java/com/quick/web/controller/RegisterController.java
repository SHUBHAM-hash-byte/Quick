package com.quick.web.controller;


import com.quick.web.Entity.User;
import com.quick.web.dto.request.RegisterDto;
import com.quick.web.dto.response.UserResponse;
import com.quick.web.service.RegisterService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class RegisterController {


    private final RegisterService registerService;

    RegisterController(RegisterService registerService){
        this.registerService=registerService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterDto registerDto){


        UserResponse userResponse=registerService.register(registerDto);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getUsers()
    {
        List<UserResponse> userResponse=registerService.getUsers();
        System.out.println("Entities found in DB: " + userResponse.size());
        return ResponseEntity.ok(userResponse);

    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable int id)
    {
        UserResponse userResponse=registerService.getUser(id);

        return ResponseEntity.ok(userResponse);

    }
    @PatchMapping("user/{id}")
    public ResponseEntity<UserResponse> updateUserData(@PathVariable int id,@RequestBody RegisterDto registerDto ){

        UserResponse userResponse=registerService.updateUserData(id,registerDto);
        return ResponseEntity.ok(userResponse);
    }


}
