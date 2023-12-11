package com.vahner.airticketsapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class Airport {
    private UUID id;
    private String nameAirPort;
    private String country;
    private char address;
    private Airliners airliners;
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
