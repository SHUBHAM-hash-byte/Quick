package com.quick.web.mapper;

import com.quick.web.Entity.User;
import com.quick.web.dto.request.RegisterDto;
import com.quick.web.dto.response.UserResponse;
import org.springframework.stereotype.Component;


@Component

public class UserMapper {

public User toEntity(RegisterDto registerDto){    User user=new User();
user.setEmail(registerDto.getEmail());
    user.setPassword(registerDto.getPassword());

return user;
}

public UserResponse toDto(User user){

    return new UserResponse(user.getId(),user.getEmail(),
            user.getPassword());
}
}