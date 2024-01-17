package com.vahner.airticketsapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "airport")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Airport {
    @Id
    @JdbcTypeCode(SqlTypes.BINARY)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name_airport")
    private String nameAirPort;

    @Column(name = "country")
    private String country;

    @Column(name = "address")
    private String address;

    @ManyToOne
    @JoinColumn(name = "airline_id")
    private Airline airline;

    @OneToMany(mappedBy = "airport")
    private List<Ticket> tickets;

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
                ", tickets=" + tickets +
                ", airline=" + airline +
                '}';
    }
}