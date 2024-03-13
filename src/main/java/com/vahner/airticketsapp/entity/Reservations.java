package com.vahner.airticketsapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "reservations")
@AllArgsConstructor
@NoArgsConstructor
public class Reservations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private UUID id;

    @Column(name = "reservations_reference")
    private String reservationsReference;

    @Column(name = "reservations_date")
    private LocalDateTime reservationsDate;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ticket> tickets;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservations that = (Reservations) o;
        return Objects.equals(id, that.id) && Objects.equals(reservationsReference, that.reservationsReference)
                && Objects.equals(reservationsDate, that.reservationsDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reservationsReference, reservationsDate);
    }

    @Override
    public String toString() {
        return "Reservations{" +
                "id=" + id +
                ", reservationsReference='" + reservationsReference + '\'' +
                ", reservationsDate=" + reservationsDate +
                ", tickets=" + tickets +
                '}';
    }
}
