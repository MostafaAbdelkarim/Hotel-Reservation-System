package com.mostafaeldahshan.hotel_reservation_system.service;

import com.mostafaeldahshan.hotel_reservation_system.model.Reservations;
import com.mostafaeldahshan.hotel_reservation_system.repos.CapacityRepository;
import com.mostafaeldahshan.hotel_reservation_system.repos.ReservationsRepository;
import com.mostafaeldahshan.hotel_reservation_system.repos.UserRepository;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class ReservationsService {

    private final ReservationsRepository reservationRepository;
    private final CapacityRepository capacityRepository;

    public ReservationsService(final ReservationsRepository reservationRepository,
                              final CapacityRepository capacityRepository) {
        this.reservationRepository = reservationRepository;
        this.capacityRepository = capacityRepository;
    }

    public List<Reservations> findAll() {
        return reservationRepository.findAll();
    }

    public Reservations get(final Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final Reservations reservation) {
        int capacity = capacityRepository.findByRoomType(reservation.getRoomType()).getCapacity();
        int overlappingReservations = reservationRepository
                .findReservationsByReservationDateAndStartTimeBeforeAndEndTimeAfterOrStartTimeBetween(
                        reservation.getReservationDate(),
                        reservation.getStartTime(), reservation.getEndTime(),
                        reservation.getStartTime(), reservation.getEndTime()).size();

        if (overlappingReservations >= capacity) {
            throw new IllegalStateException("This room's capacity is full at desired time");
        }

        return reservationRepository.save(reservation).getId();
    }

    public void update(final Long id, final Reservations reservation) {
        final Reservations existingReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        reservationRepository.save(reservation);
    }

    public void delete(final Long id) {
        reservationRepository.deleteById(id);
    }

}
