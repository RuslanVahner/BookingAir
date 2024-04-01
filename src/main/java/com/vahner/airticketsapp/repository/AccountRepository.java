package com.vahner.airticketsapp.repository;

import com.vahner.airticketsapp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account,UUID> {

    Boolean existsByLogin(String login);
    Optional<Account> findByLogin(String login);

}