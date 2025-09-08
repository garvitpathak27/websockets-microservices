package com.example.auth_service.Controller;


import com.example.auth_service.Repository.AuthRepository;
import com.example.auth_service.Service.AuthService;
import com.example.auth_service.entity.AuthUser;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth-user")
public class AuthController {
    private final AuthService authService;
    public AuthController (AuthService authService){
        this.authService = authService;

    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody AuthUser user){
        if(authService.existByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body("Username already exists in database");
        }
        authService.createUser(user);
        return ResponseEntity.ok("User Registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody AuthUser loginrequest){
        Optional<AuthUser> user  = authService.findByUsername(loginrequest.getUsername());
        if(user.isPresent() &&  user.get().getPassword().equals(loginrequest.getPassword())){
            return ResponseEntity.ok("Login Successfully");
        }
        else{
            return ResponseEntity.badRequest().body("Invalid username or password");
        }
    }

    @GetMapping
    public List<AuthUser> getAllUser(){
        return  authService.getAllUser();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthUser> getUserById(@PathVariable Long id){
        Optional<AuthUser> user = authService.getUserById(id);
        if(user.isPresent()){
            return ResponseEntity.ok(user.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthUser> updateUser(@PathVariable Long id , @RequestBody AuthUser userDetails){
        Optional<AuthUser> user = authService.getUserById(id);
        if(user.isPresent()){
            AuthUser existingUser = user.get();
            existingUser.setUsername(userDetails.getUsername());
            existingUser.setPassword(userDetails.getPassword());
            return ResponseEntity.ok(authService.updateUser(existingUser));
        }
        else{
            return  ResponseEntity.notFound().build();
        }
    }


    // deleting user
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        if(authService.getUserById(id).isPresent()){
            authService.deleteUser(id);
            return ResponseEntity.ok("User deleted successfully");
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
