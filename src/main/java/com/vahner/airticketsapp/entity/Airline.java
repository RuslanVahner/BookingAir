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

@Entity
@Table(name = "airline")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Airline {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "airline_name")
    private String airlineName;

    @Column(name = "airline_price")
    private BigDecimal airlinePrice;

    @OneToMany(mappedBy = "airline")
    private List<Trips> trips;

    @OneToMany(mappedBy = "airline")
    private List<Airport> airports;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airline airline = (Airline) o;
        return Objects.equals(id, airline.id) && Objects.equals(airlineName, airline.airlineName) && Objects.equals(airlinePrice, airline.airlinePrice);
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
                ", airports=" + airports +
                ", trips=" + trips +
                '}';
    }
}