package com.example.passwordmanagementsystem.models.data;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Password {
    @Id
    private String id;
    private String password;
    private String username;
    private String site;
}
