package com.mostafaeldahshan.hotel_reservation_system.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ReservationsDTO {

    private Long id;

    private LocalDate reservationDate;

    private LocalDate startTime;

    @Schema(type = "string", example = "14:30")
    private LocalTime endTime;

    private OffsetDateTime dateCreated;

    private OffsetDateTime lastUpdated;

    @NotNull
    private Long user;

}
