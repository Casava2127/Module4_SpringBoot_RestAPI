package com.ra.repository;


import com.ra.model.TokenBlackList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<TokenBlackList,Long> {
    Boolean existsByToken(String token);
}
