package com.vahner.airticketsapp.entity;

import com.vahner.airticketsapp.entity.enums.PassengerType;
import com.vahner.airticketsapp.entity.enums.TicketClass;
import com.vahner.airticketsapp.entity.enums.TicketStatus;
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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private UUID ticketId;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "purchase_time")
    private LocalDateTime purchaseTime;

    @Column(name = "ticket_number")
    private int ticketNumber;

    @Column(name = "ticket_class")
    @Enumerated(EnumType.STRING)
    private TicketClass ticketClass;

    @Column(name = "ticket_status")
    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private PassengerType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id", referencedColumnName = "id")
    private Flight flight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id", referencedColumnName = "id")
    private Reservations reservation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passenger_id", referencedColumnName = "id")
    private Passenger passenger;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return ticketNumber == ticket.ticketNumber && Objects.equals(ticketId, ticket.ticketId)
                && Objects.equals(price, ticket.price)
                && Objects.equals(purchaseTime, ticket.purchaseTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId, price, purchaseTime, ticketNumber);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + ticketId +
                ", price=" + price +
                ", purchaseTime=" + purchaseTime +
                ", ticketNumber=" + ticketNumber +
                ", ticketClass=" + ticketClass +
                ", ticketStatus=" + ticketStatus +
                ", type=" + type +
                ", flight=" + flight +
                ", account=" + account +
                ", reservation=" + reservation +
                ", passenger=" + passenger +
                ", cart=" + cart +
                '}';
    }
}