package com.vahner.airticketsapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "cart")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id")
    private UUID id;

    @Column(name = "total_cost")
    private BigDecimal totalCost;

    @OneToMany(mappedBy = "account",fetch = FetchType.LAZY,
    orphanRemoval = true,cascade = {MERGE, PERSIST, REFRESH})
    private List<Ticket> ticketList;

    @OneToOne(cascade = {MERGE, PERSIST, REFRESH})
    @Column(name = "onwer_id")
    private Passenger owner;

    @Column(name = "is_active")
    private boolean isRefunded;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(id, cart.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", totalCost=" + totalCost +
                ", ticketList=" + ticketList +
                ", owner=" + owner +
                '}';
    }
}