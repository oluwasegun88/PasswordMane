package com.example.passwordmanagementsystem.dto;

import lombok.Data;

@Data
public class CreatePasswordDto {
    private String password;
    private String url;
    private String username;
}
