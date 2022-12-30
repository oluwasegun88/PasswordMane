package com.example.passwordmanagementsystem.models.repository;

import com.example.passwordmanagementsystem.models.data.Password;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PasswordRepository extends MongoRepository<Password,String> {
    List<Password> findPasswordByUsername(String username);
}
