package com.mostafaeldahshan.hotel_reservation_system.repos;

import com.mostafaeldahshan.hotel_reservation_system.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}
