package com.mostafaeldahshan.hotel_reservation_system.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import javax.persistence.*;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservations {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate reservationDate;

    @DateTimeFormat(pattern = "HH:mm")
    @Column
    private LocalTime startTime;

    @DateTimeFormat(pattern = "HH:mm")
    @Column
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoomType roomType;

    @PrePersist
    public void prePersist() {
        dateCreated = OffsetDateTime.now();
        lastUpdated = dateCreated;
    }

    @PreUpdate
    public void preUpdate() {
        lastUpdated = OffsetDateTime.now();
    }

    public Reservations(LocalDate reservationDate, LocalTime startTime,
                       LocalTime endTime, User user, RoomType roomType) {
        this.reservationDate = reservationDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.user = user;
        this.roomType = roomType;
    }
}