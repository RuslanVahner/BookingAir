package com.vahner.airticketsapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
//@Table(name = "refresh_tokens")
public class RefreshToken {

    @Id
    private String login;
    private String token;

    public RefreshToken() {

    }

    public RefreshToken(String login, String token) {
        this.login = login;
        this.token = token;
    }

}
