package com.vahner.airticketsapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
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

    @Column(name = "flight_number")
    private int flightNumber;

    @Column(name = "departure_time")
    private LocalDateTime departure;

    @Column(name = "arrival_time")
    private LocalDateTime arrival;

    @Column(name = "flight_time")
    private LocalDateTime flightTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trips trips = (Trips) o;
        return Objects.equals(id, trips.id) && Objects.equals(nameTrips, trips.nameTrips) &&
               Objects.equals(flightNumber, trips.flightNumber) &&
               Objects.equals(departure, trips.departure) && Objects.equals(arrival, trips.arrival) &&
               Objects.equals(flightTime, trips.flightTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameTrips, flightNumber, departure, arrival, flightTime);
    }

    @Override
    public String toString() {
        return "Trips{" +
                "id=" + id +
                ", nameTrips='" + nameTrips + '\'' +
                ", flightNumber='" + flightNumber + '\'' +
                ", departure='" + departure + '\'' +
                ", arrival='" + arrival + '\'' +
                ", flightTime='" + flightTime + '\'' +
                '}';
    }
}