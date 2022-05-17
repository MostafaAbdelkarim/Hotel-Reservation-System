package com.mostafaeldahshan.hotel_reservation_system.repos;

import com.mostafaeldahshan.hotel_reservation_system.model.Reservations;
import com.mostafaeldahshan.hotel_reservation_system.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


public interface ReservationsRepository extends JpaRepository<Reservations, Long> {
    List<Reservations> findReservationsByRoomType(RoomType roomType);

    List<Reservations> findReservationsByReservationDateAndStartTimeBeforeAndEndTimeAfterOrStartTimeBetween
            (LocalDate reservationDate, LocalTime startTime, LocalTime endTime, LocalTime betweenStart, LocalTime betweenEnd);
}
