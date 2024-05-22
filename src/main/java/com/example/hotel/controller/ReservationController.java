package com.example.hotel.controller;
import com.example.hotel.reservationDTO.ReservationDTO;
import com.example.hotel.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;
    @PostMapping("/{username}/{hotelName}")
    public String makeReservation(@PathVariable("username") String username, @PathVariable("hotelName") String hotelName) {
        return reservationService.makeReservation(username, hotelName);
    }
    @GetMapping("/all")
    public List<ReservationDTO> getAllReservations() {
        return reservationService.getAllReservations();
    }
}
