package com.vahner.airticketsapp.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.Serializable;

@Getter
@RequiredArgsConstructor
public enum Role implements Serializable {
    ADMIN("ADMIN"),
    PASSENGER("PASSENGER");

    private final String role;

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {

    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {

    }

}
