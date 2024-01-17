package com.vahner.airticketsapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.util.List;
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
    @JdbcTypeCode(SqlTypes.BINARY)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "total_cost")
    private BigDecimal totalCost;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Passenger owner;

    @OneToMany(mappedBy = "cart")
    private List<Ticket> tickets;

    @Column(name = "is_refunded")
    private boolean isRefunded;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return isRefunded == cart.isRefunded &&
                Objects.equals(id, cart.id) &&
                Objects.equals(totalCost, cart.totalCost) &&
                Objects.equals(owner, cart.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalCost, owner, isRefunded);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", totalCost=" + totalCost +
                ", owner=" + owner +
                ", isRefunded=" + isRefunded +
                '}';
    }
}