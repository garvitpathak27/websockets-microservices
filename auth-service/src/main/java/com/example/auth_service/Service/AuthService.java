package com.example.auth_service.Service;

import com.example.auth_service.Repository.AuthRepository;
import com.example.auth_service.entity.AuthUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService {
    public final AuthRepository authRepository;
    public AuthService(AuthRepository authRepository){
        this.authRepository = authRepository;

    }

    public List<AuthUser> getAllUser(){
        return authRepository.findAll();
    }

    public Optional<AuthUser> getUserById(Long id){
        return authRepository.findById(id);
    }
    public void createUser(AuthUser user){
        authRepository.save(user);
    }
    public Optional<AuthUser> findByUsername(String username){
        return authRepository.findByUsername(username);
    }
    public AuthUser updateUser(AuthUser user){
        return authRepository.save(user);
    }
    public void deleteUser(Long id){
        authRepository.deleteById(id);
    }
    public boolean existByUsername(String username){
        return   authRepository.findByUsername(username).isPresent();
    }
}
