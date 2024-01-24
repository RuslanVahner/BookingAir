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
@Table(name = "airline")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "airline_name")
    private String airlineName;

    @Column(name = "airline_price")
    private BigDecimal airlinePrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trips_id")
    private Trips trips;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airline airline = (Airline) o;
        return Objects.equals(id, airline.id) && Objects.equals(airlineName, airline.airlineName)
                && Objects.equals(airlinePrice, airline.airlinePrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, airlineName, airlinePrice);
    }

    @Override
    public String toString() {
        return "Airline{" +
                "id=" + id +
                ", airlineName='" + airlineName + '\'' +
                ", airlinePrice=" + airlinePrice +
                ", trips=" + trips +
                '}';
    }
}