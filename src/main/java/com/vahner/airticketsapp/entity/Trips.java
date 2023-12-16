package com.vahner.airticketsapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Trips {
    private UUID id;
    private String nameTrips;
    private int numberTrips;
    private Double distance;
    private Double time;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trips trips = (Trips) o;
        return numberTrips == trips.numberTrips && Objects.equals(id, trips.id) &&
                Objects.equals(nameTrips, trips.nameTrips) &&
                Objects.equals(distance, trips.distance) &&
                Objects.equals(time, trips.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameTrips, numberTrips, distance, time);
    }

    @Override
    public String toString() {
        return "Trips{" +
                "id=" + id +
                ", nameTrips='" + nameTrips + '\'' +
                ", numberTrips=" + numberTrips +
                ", distance=" + distance +
                ", time=" + time +
                '}';
    }
}