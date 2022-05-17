package com.mostafaeldahshan.hotel_reservation_system.service;

import com.mostafaeldahshan.hotel_reservation_system.model.Reservations;
import com.mostafaeldahshan.hotel_reservation_system.repos.ReservationsRepository;
import com.mostafaeldahshan.hotel_reservation_system.repos.UserRepository;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class ReservationsService {

    private final ReservationsRepository reservationRepository;
    private final UserRepository userRepository;

    public ReservationsService(ReservationsRepository reservationRepository, UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
    }

    public List<Reservations> findAll() {
        return reservationRepository.findAll();
    }

    public Reservations get(final Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final Reservations reservation) {
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
