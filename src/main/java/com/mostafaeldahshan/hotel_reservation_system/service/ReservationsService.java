package com.mostafaeldahshan.hotel_reservation_system.service;

import com.mostafaeldahshan.hotel_reservation_system.domain.Reservations;
import com.mostafaeldahshan.hotel_reservation_system.domain.User;
import com.mostafaeldahshan.hotel_reservation_system.model.ReservationsDTO;
import com.mostafaeldahshan.hotel_reservation_system.repos.ReservationsRepository;
import com.mostafaeldahshan.hotel_reservation_system.repos.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class ReservationsService {

    private final ReservationsRepository reservationsRepository;
    private final UserRepository userRepository;

    public ReservationsService(final ReservationsRepository reservationsRepository,
            final UserRepository userRepository) {
        this.reservationsRepository = reservationsRepository;
        this.userRepository = userRepository;
    }

    public List<ReservationsDTO> findAll() {
        return reservationsRepository.findAll()
                .stream()
                .map(reservations -> mapToDTO(reservations, new ReservationsDTO()))
                .collect(Collectors.toList());
    }

    public ReservationsDTO get(final Long id) {
        return reservationsRepository.findById(id)
                .map(reservations -> mapToDTO(reservations, new ReservationsDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final ReservationsDTO reservationsDTO) {
        final Reservations reservations = new Reservations();
        mapToEntity(reservationsDTO, reservations);
        return reservationsRepository.save(reservations).getId();
    }

    public void update(final Long id, final ReservationsDTO reservationsDTO) {
        final Reservations reservations = reservationsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(reservationsDTO, reservations);
        reservationsRepository.save(reservations);
    }

    public void delete(final Long id) {
        reservationsRepository.deleteById(id);
    }

    private ReservationsDTO mapToDTO(final Reservations reservations,
            final ReservationsDTO reservationsDTO) {
        reservationsDTO.setId(reservations.getId());
        reservationsDTO.setReservationDate(reservations.getReservationDate());
        reservationsDTO.setStartTime(reservations.getStartTime());
        reservationsDTO.setEndTime(reservations.getEndTime());
        reservationsDTO.setDateCreated(reservations.getDateCreated());
        reservationsDTO.setLastUpdated(reservations.getLastUpdated());
        reservationsDTO.setUser(reservations.getUser() == null ? null : reservations.getUser().getId());
        return reservationsDTO;
    }

    private Reservations mapToEntity(final ReservationsDTO reservationsDTO,
            final Reservations reservations) {
        reservations.setReservationDate(reservationsDTO.getReservationDate());
        reservations.setStartTime(reservationsDTO.getStartTime());
        reservations.setEndTime(reservationsDTO.getEndTime());
        reservations.setDateCreated(reservationsDTO.getDateCreated());
        reservations.setLastUpdated(reservationsDTO.getLastUpdated());
        if (reservationsDTO.getUser() != null && (reservations.getUser() == null || !reservations.getUser().getId().equals(reservationsDTO.getUser()))) {
            final User user = userRepository.findById(reservationsDTO.getUser())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
            reservations.setUser(user);
        }
        return reservations;
    }

}
