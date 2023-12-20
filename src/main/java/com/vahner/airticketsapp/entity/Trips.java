package com.vahner.airticketsapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;
@Entity
@Table(name = "trips")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Trips {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name_trips")
    private String nameTrips;

    @Column(name = "number_trips")
    private int numberTrips;

    @Column(name = "distance")
    private Double distance;

    @Column(name = "time")
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