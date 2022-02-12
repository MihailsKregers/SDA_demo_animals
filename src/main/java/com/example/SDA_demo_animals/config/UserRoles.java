package com.example.SDA_demo_animals.config;

public enum UserRoles {
    ADMIN("ADMIN"),
    USER("USER"),
    JSON_USER("JSON_USER");

    private final static String prefix = "ROLE_";

    private final String key;

    UserRoles(String key) {
        this.key = key;
    }

    public final String getKey() {
        return key;
    }

    public String getAuthority() {
        return prefix+getKey();
    }
}
