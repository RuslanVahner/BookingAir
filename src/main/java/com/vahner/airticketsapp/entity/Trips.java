package com.vahner.airticketsapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.List;
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
    @JdbcTypeCode(SqlTypes.BINARY)
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

    @ManyToOne
    @JoinColumn(name = "airline_id")
    private Airline airline;

    @OneToMany(mappedBy = "trip")
    private List<Ticket> tickets;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trips trips = (Trips) o;
        return flightNumber == trips.flightNumber && // Исправлено здесь
                Objects.equals(id, trips.id) && Objects.equals(nameTrips, trips.nameTrips) &&
                Objects.equals(departure, trips.departure) && Objects.equals(arrival, trips.arrival) &&
                Objects.equals(flightTime, trips.flightTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameTrips, flightNumber, departure, arrival, flightTime); // Исправлено здесь
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
