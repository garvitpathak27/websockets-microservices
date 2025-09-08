package com.example.auth_service.Repository;

import com.example.auth_service.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<AuthUser,Long> {
    Optional<AuthUser> findByUsername(String username);
}
