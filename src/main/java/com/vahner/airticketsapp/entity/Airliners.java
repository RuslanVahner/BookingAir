package com.vahner.airticketsapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class Airliners {
    private UUID id;
    private String nameAirLin;
    private BigDecimal airlinePrice;
    private Trips trips;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airliners airliners = (Airliners) o;
        return Objects.equals(id, airliners.id) && Objects.equals(nameAirLin, airliners.nameAirLin) && Objects.equals(airlinePrice, airliners.airlinePrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameAirLin, airlinePrice);
    }
}