package com.vahner.airticketsapp.entity.enums;

import lombok.Getter;

@Getter
public enum PassegerType {
    ADULT("Adult"),
    CHILD("Child");

    private final String type;

    PassegerType(String type) {
        this.type = type;
    }
}