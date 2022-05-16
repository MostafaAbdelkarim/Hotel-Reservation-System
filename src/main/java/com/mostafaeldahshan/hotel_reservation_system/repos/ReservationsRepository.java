package com.mostafaeldahshan.hotel_reservation_system.repos;

import com.mostafaeldahshan.hotel_reservation_system.domain.Reservations;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReservationsRepository extends JpaRepository<Reservations, Long> {
}
