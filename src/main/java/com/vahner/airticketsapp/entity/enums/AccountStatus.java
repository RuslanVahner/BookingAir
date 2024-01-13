package com.vahner.airticketsapp.entity.enums;

import lombok.Getter;

@Getter
public enum AccountStatus {
    ACTIVE(0),
    BLOCKED(2),
    REMOVED(4);
    private final int value;

    AccountStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
