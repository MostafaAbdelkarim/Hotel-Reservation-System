package com.mostafaeldahshan.hotel_reservation_system.repos;

import com.mostafaeldahshan.hotel_reservation_system.model.Capacity;
import com.mostafaeldahshan.hotel_reservation_system.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CapacityRepository extends JpaRepository<Capacity, Long> {

    Capacity findByRoomType(RoomType roomType);
}