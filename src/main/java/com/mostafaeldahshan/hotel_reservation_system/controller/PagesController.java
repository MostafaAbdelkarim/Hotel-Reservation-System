package com.mostafaeldahshan.hotel_reservation_system.controller;

import com.mostafaeldahshan.hotel_reservation_system.model.Reservations;
import com.mostafaeldahshan.hotel_reservation_system.model.User;
import com.mostafaeldahshan.hotel_reservation_system.service.ReservationsService;
import com.mostafaeldahshan.hotel_reservation_system.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
import java.util.Set;

@Controller
public class PagesController {

    final UserService userService;
    final ReservationsService reservationService;

    public PagesController(UserService userService, ReservationsService reservationService) {
        this.userService = userService;
        this.reservationService = reservationService;
    }

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/reservations")
    public String reservations(Model model, HttpSession session) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = principal.getUsername();
        User user = userService.getUserByUsername(name);

        // This should always be the case
        if (user != null) {
            session.setAttribute("user", user);

            // Empty reservation object in case the user creates a new reservation
            Reservations reservation = new Reservations();
            model.addAttribute("reservation", reservation);
            return "reservations";
        }
        return "index";
    }

    @PostMapping("/reservations-submit")
    public String reservationsSubmit(@ModelAttribute Reservations reservation, Model model, @SessionAttribute("user") User user) {
        // Save to DB after updating
        assert user != null;
        reservation.setUser(user);
        reservationService.create(reservation);
        Set<Reservations> userReservations = user.getReservations();
        userReservations.add(reservation);
        user.setReservations(userReservations);
        userService.update(user.getId(), user);
        return "redirect:/reservations";
    }

    @GetMapping("/get/all-reservations")
    public String getAllReservations()
    {
        return "reservationsList";
    }
}
