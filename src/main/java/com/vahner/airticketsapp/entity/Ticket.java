package com.vahner.airticketsapp.entity;

import com.vahner.airticketsapp.entity.enums.ClasService;
import com.vahner.airticketsapp.entity.enums.PassegerType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "ticket")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "data")
    private char data;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "passenger_number")
    private int passegerNumber;

    @Column(name = "account_id")
    @OneToOne(cascade = {MERGE, PERSIST, REFRESH})
    private Account account;


    @Column(name = "service")
    @Enumerated(EnumType.STRING)
    private ClasService service;


    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private PassegerType type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return data == ticket.data && isActive == ticket.isActive &&
                passegerNumber == ticket.passegerNumber &&
                Objects.equals(id, ticket.id) && Objects.equals(price, ticket.price) &&
                service == ticket.service && type == ticket.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, data, isActive, passegerNumber, service, type);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", price=" + price +
                ", data=" + data +
                ", isActive=" + isActive +
                ", numbPasseger=" + passegerNumber +
                ", account=" + account +
                ", service=" + service +
                ", type=" + type +
                '}';
    }
}