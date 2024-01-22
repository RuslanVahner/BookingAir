package com.vahner.airticketsapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vahner.airticketsapp.entity.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

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

    @Column(name = "account_status")
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @OneToMany(mappedBy = "account")
    private Set<Ticket> tickets;

    @OneToMany(mappedBy = "account")
    private List<Cart> carts;

    @ManyToOne
    @JoinColumn(name = "passenger_id")
    @JsonBackReference
    private Passenger passenger;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(login, account.login) && Objects.equals(password, account.password) && Objects.equals(balance, account.balance) && status == account.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, balance, status);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", status=" + status +
                ", carts=" + carts +
                ", passenger=" + passenger +
                '}';
    }
}