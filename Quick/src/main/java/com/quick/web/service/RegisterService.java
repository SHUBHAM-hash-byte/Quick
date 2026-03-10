package com.quick.web.service;

import com.quick.web.Entity.User;
import com.quick.web.dto.request.RegisterDto;
import com.quick.web.dto.response.UserResponse;
import com.quick.web.mapper.UserMapper;
import com.quick.web.repo.RegisterRepo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class RegisterService {


    RegisterRepo registerRepo;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    AuthenticationManager authenticationManager;

    RegisterService(RegisterRepo registerRepo, UserMapper userMapper, PasswordEncoder passwordEncoder,AuthenticationManager authenticationManager){
     this.registerRepo=registerRepo;
     this.userMapper=userMapper;
     this.passwordEncoder= passwordEncoder;
     this.authenticationManager=authenticationManager;
    }

    public  UserResponse register(RegisterDto registerDto) {

        if(registerRepo.findByEmail(registerDto.getEmail()).isPresent())
        {
            throw new RuntimeException("email registered");
       }
        User user=userMapper.toEntity(registerDto);

         user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

         User saveuser=registerRepo.save(user);

         return userMapper.toDto(user);
    }




    public List<UserResponse> getUsers() {

       List<User> users = registerRepo.findAll();


        return users.stream().map(user -> userMapper.toDto(user)).collect(Collectors.toList());

    }

    public UserResponse getUser(int id) {

        User user=registerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("No user"+id));

        return userMapper.toDto(user);




    }

    public UserResponse updateUserData(int id, RegisterDto registerDto) {

        User user= registerRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("No user"));

        if(registerDto.getEmail() !=  null){
            user.setEmail(registerDto.getEmail());
        }


        if(registerDto.getPassword() !=  null){
            user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        }

        User updateUser=registerRepo.save(user);

        return userMapper.toDto(updateUser);

    }

    public UserResponse login(RegisterDto loginDto) {
        User user=registerRepo.findByEmail(loginDto.getEmail()).
                orElseThrow(() -> new RuntimeException("wrong cred"));

        if(!passwordEncoder.matches(loginDto.getPassword(),user.getPassword()))
        {
            throw new RuntimeException("Invalid user");
        }


         return userMapper.toDto(user);


    }
}
