package com.example.SDA_demo_animals.config;

public enum Authorities {
    SHOW_MAIN_PAGE("SHOW_MAIN_PAGE");

    private String key;

    Authorities(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
