package com.vahner.airticketsapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vahner.airticketsapp.entity.enums.ClasServiceType;
import com.vahner.airticketsapp.entity.enums.PassegerType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "ticket")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "data")
    private LocalDateTime data;

    @Column(name = "ticket_number")
    private int ticketNumber;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id")
    private Trips trip;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "service")
    @Enumerated(EnumType.STRING)
    private ClasServiceType service;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private PassegerType type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return data == ticket.data && ticketNumber == ticket.ticketNumber &&
                Objects.equals(id, ticket.id) && Objects.equals(price, ticket.price) &&
                service == ticket.service && type == ticket.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, data, ticketNumber, service, type);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", price=" + price +
                ", data=" + data +
                ", numbPasseger=" + ticketNumber +
                ", account=" + account +
                ", trip=" + trip +
                ", service=" + service +
                ", type=" + type +
                '}';
    }
}