package com.mostafaeldahshan.hotel_reservation_system.rest;

import com.mostafaeldahshan.hotel_reservation_system.model.ReservationsDTO;
import com.mostafaeldahshan.hotel_reservation_system.service.ReservationsService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/reservationss", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReservationsController {

    private final ReservationsService reservationsService;

    public ReservationsController(final ReservationsService reservationsService) {
        this.reservationsService = reservationsService;
    }

    @GetMapping
    public ResponseEntity<List<ReservationsDTO>> getAllReservationss() {
        return ResponseEntity.ok(reservationsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationsDTO> getReservations(@PathVariable final Long id) {
        return ResponseEntity.ok(reservationsService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createReservations(
            @RequestBody @Valid final ReservationsDTO reservationsDTO) {
        return new ResponseEntity<>(reservationsService.create(reservationsDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateReservations(@PathVariable final Long id,
            @RequestBody @Valid final ReservationsDTO reservationsDTO) {
        reservationsService.update(id, reservationsDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteReservations(@PathVariable final Long id) {
        reservationsService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
