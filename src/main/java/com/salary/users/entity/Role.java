package com.salary.users.entity;

import com.salary.global.EnumModel;

public enum Role implements EnumModel {
    USER("user");

    private final String value;

    Role(String value) {
        this.value = value;
    }


    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return value;
    }
}
