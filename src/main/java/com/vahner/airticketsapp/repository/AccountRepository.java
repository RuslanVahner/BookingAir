package com.vahner.airticketsapp.repository;

import com.vahner.airticketsapp.entity.Account;
import com.vahner.airticketsapp.entity.enums.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    //        List<Account> findAccountByStatus (AccountStatus status);
    @Query("")
    Account getAccountById(UUID id);
}
