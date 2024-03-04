package com.vahner.airticketsapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "passenger")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Passenger {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age")
    private int age;

    @Column(name = "phone")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return age == passenger.age && Objects.equals(id, passenger.id)
                && Objects.equals(email, passenger.email)
                && Objects.equals(firstName, passenger.firstName)
                && Objects.equals(lastName, passenger.lastName)
                && Objects.equals(phone, passenger.phone);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, firstName, lastName, age, phone);
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", account=" + account +
                '}';
    }
}