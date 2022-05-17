package com.mostafaeldahshan.hotel_reservation_system.exception;

public class CapacityFullException extends RuntimeException {
    public CapacityFullException(String message) {
        super(message);
    }
}