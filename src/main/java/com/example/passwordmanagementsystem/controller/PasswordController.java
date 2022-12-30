package com.example.passwordmanagementsystem.controller;

import com.example.passwordmanagementsystem.dto.CreatePasswordDto;
import com.example.passwordmanagementsystem.dto.UpdatePasswordRequest;
import com.example.passwordmanagementsystem.service.PasswordService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/password")
@AllArgsConstructor
public class PasswordController {
    private final PasswordService passwordService;
    @PostMapping("/create")
    public ResponseEntity<?> createPassword(@RequestBody CreatePasswordDto dto){
        try{
            return ResponseEntity.ok().body(passwordService.createNewPassword(dto));
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getLocalizedMessage());
        }
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updatePassword(@PathVariable String id, @RequestBody UpdatePasswordRequest request){
        try{
            return ResponseEntity.ok().body(passwordService.updatePassword(id,request));
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getLocalizedMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>deletePassword(@PathVariable String id){
        try {
            return ResponseEntity.ok().body(passwordService.deletePasswordById(id));
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getLocalizedMessage());
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getPassword(@PathVariable String id){
        try{
            return ResponseEntity.ok().body(passwordService.getPasswordById(id));
        }catch (IllegalArgumentException  e){
            return ResponseEntity.badRequest().body(e.getLocalizedMessage());
        }
    }
}
