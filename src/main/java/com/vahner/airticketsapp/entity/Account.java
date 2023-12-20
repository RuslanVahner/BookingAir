package com.vahner.airticketsapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;

@Setter
@Getter
@Entity
@Table(name = "account")
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "balance")
    private BigDecimal balance;

    @OneToOne(cascade = {MERGE, PERSIST, REFRESH})
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

    @OneToOne(cascade = {MERGE, PERSIST, REFRESH})
    @JoinColumn(name = "passenger_id", referencedColumnName = "id")
    private Passenger passenger;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(login, account.login) &&
                Objects.equals(password, account.password) &&
                Objects.equals(balance, account.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, balance);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", cart=" + cart +
                ", passenger=" + passenger +
                '}';
    }
}