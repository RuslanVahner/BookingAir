package com.vahner.airticketsapp.repository;

import com.vahner.airticketsapp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account,UUID> {

    @Query("SELECT a FROM Account a WHERE a.login = ?1")
    Optional<Account> findByName(String username);
}