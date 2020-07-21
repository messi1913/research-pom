package com.hirit.research.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hirit.research.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByEmail(String email);

}
