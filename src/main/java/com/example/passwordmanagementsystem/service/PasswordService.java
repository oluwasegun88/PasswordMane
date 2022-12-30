package com.example.passwordmanagementsystem.service;

import com.example.passwordmanagementsystem.dto.CreatePasswordDto;
import com.example.passwordmanagementsystem.dto.UpdatePasswordRequest;
import com.example.passwordmanagementsystem.models.data.Password;
import com.example.passwordmanagementsystem.models.repository.PasswordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PasswordService {
    private final PasswordRepository passwordRepository;

    public Password createNewPassword(CreatePasswordDto dto) {
        validateCreatePasswordDto(dto);
        Password password = new Password();
        password.setPassword(dto.getPassword());
        password.setUsername(dto.getUsername());
        password.setSite(dto.getUrl());
       return passwordRepository.save(password);
    }

    private void validateCreatePasswordDto(CreatePasswordDto dto){
        if (dto.getPassword().isBlank()|| dto.getPassword().isEmpty()){
            throw new IllegalArgumentException("Password cannot be blank or empty");
        }
        if (dto.getUsername().isEmpty() || dto.getUsername().isBlank()){
            throw new IllegalArgumentException("Username cannot be empty or blank");
        }
    }


    public Password updatePassword(String id, UpdatePasswordRequest request){
        if (id.isEmpty() || id.isBlank()){
            throw new IllegalArgumentException("Id cannot be blank or empty");
        }
        validateUpdatePasswordRequest(request);
        Optional<Password> foundPassWord = passwordRepository.findById(id);
        if (foundPassWord.isPresent()){
            if (!request.getPassword().isBlank() || !request.getPassword().isEmpty()){
                foundPassWord.get().setPassword(request.getPassword());
            }
            if (!request.getUsername().isBlank() || !request.getUsername().isEmpty()){
                foundPassWord.get().setUsername(request.getUsername());
            }
            if (!request.getUrl().isBlank() || !request.getUrl().isEmpty()){
                foundPassWord.get().setSite(request.getUrl());
            }
            return passwordRepository.save(foundPassWord.get());
        }

        throw new IllegalArgumentException("Password not found");
    }

    private void validateUpdatePasswordRequest(UpdatePasswordRequest request){
        if ((request.getPassword().isBlank() || request.getPassword().isEmpty()) &&
                (request.getUsername().isEmpty() || request.getUsername().isBlank())&&
                (request.getUrl().isBlank() || request.getUrl().isEmpty())){
            throw new IllegalArgumentException("All fields cannot be empty or blank");
        }
    }

    public String deletePasswordById(String id){
        if (id.isEmpty() || id.isBlank()){
            throw new IllegalArgumentException("Id cannot be empty or blank");
        }

        passwordRepository.deleteById(id);

        return "password deleted successfully";
    }

    public Password getPasswordById(String id){
        if (id.isBlank() || id.isEmpty()){
            throw new IllegalArgumentException("id cannot be empty or blank");
        }
        Optional<Password>foundPassword = passwordRepository.findById(id);
        if (foundPassword.isPresent()){
            return foundPassword.get();
        }
        throw new IllegalArgumentException("Password not found");
    }
}
