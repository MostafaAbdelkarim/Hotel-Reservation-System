package com.mostafaeldahshan.hotel_reservation_system.model;

public enum RoomType {
    SINGLE("SINGLE"), DOUBLE("DOUBLE"), TRIPLE("TRIPLE");

    private final String name;

    private RoomType(String value) {
        name = value;
    }

    @Override
    public String toString() {
        return name;
    }
}
