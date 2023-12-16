package com.vahner.airticketsapp.entity;

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
public class Airline {
    private UUID id;
    private String airlinName;
    private BigDecimal airlinePrice;
    private Trips trips;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airline airliners = (Airline) o;
        return Objects.equals(id, airliners.id) && Objects.equals(airlinName, airliners.airlinName) && Objects.equals(airlinePrice, airliners.airlinePrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, airlinName, airlinePrice);
    }

    @Override
    public String toString() {
        return "Airliners{" +
                "id=" + id +
                ", nameAirLin='" + airlinName + '\'' +
                ", airlinePrice=" + airlinePrice +
                ", trips=" + trips +
                '}';
    }
}