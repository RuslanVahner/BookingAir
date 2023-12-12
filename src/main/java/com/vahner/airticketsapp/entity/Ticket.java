package com.vahner.airticketsapp.entity;

import com.vahner.airticketsapp.entity.enums.ClasService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class Ticket {
    private UUID id;
    private BigDecimal price;
    private char data;
    private boolean status;
    private Account account;
    private ClasService service;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return status == ticket.status && Objects.equals(id, ticket.id) &&
                Objects.equals(price, ticket.price) &&
                Objects.equals(data, ticket.data) &&
                service == ticket.service;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, data, status, service);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", price=" + price +
                ", data=" + data +
                ", status=" + status +
                ", account=" + account +
                ", service=" + service +
                '}';
    }
}