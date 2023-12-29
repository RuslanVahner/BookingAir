package com.vahner.airticketsapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "airport")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name_airport")
    private String nameAirPort;

    @Column(name = "country")
    private String country;

    @Column(name = "address")
    private char address;

    @OneToOne(cascade = {MERGE,PERSIST,REFRESH})
    @JoinColumn(name = "airline_id")
    private Airline airliners;

    @OneToOne(cascade = {MERGE,PERSIST,REFRESH})
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airport airport = (Airport) o;
        return Objects.equals(id, airport.id) && Objects.equals(nameAirPort, airport.nameAirPort) &&
                Objects.equals(country, airport.country) &&
                Objects.equals(address, airport.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameAirPort, country, address);
    }

    @Override
    public String toString() {
        return "Airport{" +
                "id=" + id +
                ", nameAirPort='" + nameAirPort + '\'' +
                ", country='" + country + '\'' +
                ", address=" + address +
                ", airliners=" + airliners +
                ", ticket=" + ticket +
                '}';
    }
}