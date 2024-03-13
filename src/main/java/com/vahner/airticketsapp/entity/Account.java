package com.vahner.airticketsapp.entity;

import com.vahner.airticketsapp.entity.enums.AccountStatus;
import com.vahner.airticketsapp.entity.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Table(name = "account")
@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "owner")
    private String owner;

    @Column(name = "create_account_date")
    private LocalDate createAccountDate;

    @Column(name = "account_status")
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Set<Role> role;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Passenger> passengers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(login, account.login)
                && Objects.equals(balance, account.balance)
                && Objects.equals(owner, account.owner)
                && Objects.equals(createAccountDate, account.createAccountDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, balance, owner, createAccountDate);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", owner='" + owner + '\'' +
                ", createAccountDate=" + createAccountDate +
                ", status=" + status +
                ", role=" + role +
                ", passengers=" + passengers +
                '}';
    }
}