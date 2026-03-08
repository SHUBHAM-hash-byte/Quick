package com.quick.web.dto.response;

public class UserResponse {
    private int id;
    private String email;
    private String password;

    // 1. You need a No-Args Constructor for Jackson
    public UserResponse() {}

    // 2. Your All-Args Constructor should include the ID
    public UserResponse(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }


}
