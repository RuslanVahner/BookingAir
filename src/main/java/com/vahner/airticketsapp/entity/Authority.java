package com.vahner.airticketsapp.entity;


import com.vahner.airticketsapp.entity.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "authority")
@AllArgsConstructor
@NoArgsConstructor
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;

    @Column(name = "authority")
    private String authority;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authority authority1 = (Authority) o;
        return Objects.equals(id, authority1.id) && Objects.equals(authority, authority1.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authority);
    }

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
                ", authority='" + authority + '\'' +
                ", roles=" + roles +
                '}';
    }
}