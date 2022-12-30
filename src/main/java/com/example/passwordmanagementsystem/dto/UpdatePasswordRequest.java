package com.example.passwordmanagementsystem.dto;

import lombok.Data;

@Data
public class UpdatePasswordRequest {
    private String password;
    private String url;
    private String username;
}
