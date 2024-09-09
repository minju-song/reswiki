package com.teddybear.reswiki.auth.repository;

import com.teddybear.reswiki.auth.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Auth, String> {
}
