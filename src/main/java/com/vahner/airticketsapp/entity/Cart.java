package com.vahner.airticketsapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "cart")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "total_cost")
    private BigDecimal totalCost;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(id, cart.id) && Objects.equals(totalCost, cart.totalCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalCost);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", totalCost=" + totalCost +
                ", account=" + account +
                '}';
    }
}