package com.vahner.airticketsapp.repository;

import com.vahner.airticketsapp.entity.Account;
import com.vahner.airticketsapp.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {
    @Query("SELECT t FROM Ticket t WHERE t.account = :account")
    List<Ticket> findByAccount(Account account);
}
