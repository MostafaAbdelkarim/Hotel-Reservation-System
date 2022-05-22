package com.mostafaeldahshan.hotel_reservation_system;

import com.mostafaeldahshan.hotel_reservation_system.model.Capacity;
import com.mostafaeldahshan.hotel_reservation_system.model.RoomType;
import com.mostafaeldahshan.hotel_reservation_system.model.User;
import com.mostafaeldahshan.hotel_reservation_system.repos.CapacityRepository;
import com.mostafaeldahshan.hotel_reservation_system.repos.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashMap;
import java.util.Map;


@SpringBootApplication
@EnableSwagger2
public class HotelReservationSystemApplication {

    private Map<RoomType, Integer> initialCapacities =
            new HashMap<>() {
                {
                    put(RoomType.SINGLE, 20);
                    put(RoomType.DOUBLE, 4);
                    put(RoomType.TRIPLE, 1);
                }
            };

    public static void main(String[] args)
    {
        SpringApplication.run(HotelReservationSystemApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(
            UserRepository userRepository,
            CapacityRepository capacityRepository) {
        return (args) -> {
            userRepository.save(
                    new User("Mostafa ElDahshan", "admin", bCryptPasswordEncoder().encode("admon")));

            for (RoomType roomType : initialCapacities.keySet()) {
                capacityRepository.save(new Capacity(roomType, initialCapacities.get(roomType)));
            }
        };
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
