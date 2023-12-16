package com.vahner.airticketsapp.entity;

import com.vahner.airticketsapp.entity.enums.ClasService;
import com.vahner.airticketsapp.entity.enums.PassegerType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    private UUID id;
    private BigDecimal price;
    private char data;
    private boolean isActive;
    private int passegerNumber;
    private boolean isRefunded;
    private Account account;
    private ClasService service;
    private PassegerType type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return data == ticket.data && isActive == ticket.isActive &&
                passegerNumber == ticket.passegerNumber && isRefunded == ticket.isRefunded &&
                Objects.equals(id, ticket.id) && Objects.equals(price, ticket.price) &&
                service == ticket.service && type == ticket.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, data, isActive, passegerNumber, isRefunded, service, type);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", price=" + price +
                ", data=" + data +
                ", isActive=" + isActive +
                ", numbPasseger=" + passegerNumber +
                ", isRefunded=" + isRefunded +
                ", account=" + account +
                ", service=" + service +
                ", type=" + type +
                '}';
    }
}