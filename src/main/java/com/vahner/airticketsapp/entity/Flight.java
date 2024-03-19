package com.vahner.airticketsapp.entity;

import com.vahner.airticketsapp.entity.enums.FlightStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "flight")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name_flight")
    private String nameFlight;

    @Column(name = "flight_number")
    private int flightNumber;

    @Column(name = "departure_airport")
    private String departureAirport;

    @Column(name = "arrival_airport")
    private String arrivalAirport;

    @Column(name = "flight_time")
    private LocalTime flightTime;

    @Column(name = "departure_date")
    private LocalDate departureDate;

    @Column(name = "arrival_date")
    private LocalDate arrivalDate;

    @Column(name = "airline")
    private String airline;

    @Column(name = "flight_status")
    @Enumerated(EnumType.STRING)
    private FlightStatus flightStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return flightNumber == flight.flightNumber && Objects.equals(id, flight.id)
                && Objects.equals(nameFlight, flight.nameFlight)
                && Objects.equals(departureAirport, flight.departureAirport)
                && Objects.equals(arrivalAirport, flight.arrivalAirport)
                && Objects.equals(flightTime, flight.flightTime)
                && Objects.equals(departureDate, flight.departureDate)
                && Objects.equals(arrivalDate, flight.arrivalDate)
                && Objects.equals(airline, flight.airline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameFlight, flightNumber, departureAirport, arrivalAirport,
                flightTime, departureDate, arrivalDate, airline);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", nameFlight='" + nameFlight + '\'' +
                ", flightNumber=" + flightNumber +
                ", departureAirport='" + departureAirport + '\'' +
                ", arrivalAirport='" + arrivalAirport + '\'' +
                ", flightTime=" + flightTime +
                ", departureDate=" + departureDate +
                ", arrivalDate=" + arrivalDate +
                ", airline='" + airline + '\'' +
                ", flightStatus=" + flightStatus +
                '}';
    }
}