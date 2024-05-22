package com.example.hotel.services;

import com.example.hotel.entity.Hotel;
import com.example.hotel.entity.Person;
import com.example.hotel.entity.Reservation;
import com.example.hotel.repository.HotelRepository;
import com.example.hotel.repository.PersonRepository;
import com.example.hotel.repository.ReservationRepository;
import com.example.hotel.reservationDTO.ReservationDTO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Data
@Slf4j
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private PersonRepository personRepository;

    public String makeReservation(String username, String hotelName) {
        try {
            Optional<Person> personOptional = personRepository.findByUsername(username);
            Optional<Hotel> hotelOptional = hotelRepository.findByHotelName(hotelName);

            if (personOptional.isPresent() && hotelOptional.isPresent()) {
                Person person = personOptional.get();
                Hotel hotel = hotelOptional.get();

                if (person.getPersonBalance() >= hotel.getRoomPrice()) {
                    if (hotel.getHotelCapacity() > 0) {
                        person.setPersonBalance(person.getPersonBalance() - hotel.getRoomPrice());
                        personRepository.save(person);

                        hotel.setHotelCapacity(hotel.getHotelCapacity() - 1);
                        hotelRepository.save(hotel);

                        Reservation reservation = new Reservation();
                        reservation.setId(generateReservationId());
                        reservation.setHotelId(String.valueOf(hotel.getId()));
                        reservation.setPersonId(String.valueOf(person.getId()));
                        reservation.setName(person.getName());
                        reservation.setLastName(person.getLastName());
                        reservation.setPersonBalance(person.getPersonBalance());

                        reservationRepository.save(reservation);

                        return "Reservation successful";
                    } else {
                        return "Not enough capacity";
                    }
                } else {
                    return "Not enough balance";
                }
            } else {
                return "Person or Hotel not found";
            }
        } catch (Exception e) {
            log.error("Error while making reservation: {}", e.getMessage());
            return "Error while making reservation";
        }
    }
    private Long generateReservationId() {
        // Implementation to generate a unique id
        return UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    }
    public List<ReservationDTO> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        List<ReservationDTO> reservationDTOs = new ArrayList<>();
        for (Reservation reservation : reservations) {
            ReservationDTO dto = convertToDTO(reservation);
            reservationDTOs.add(dto);
        }
        return reservationDTOs;
    }

    private ReservationDTO convertToDTO(Reservation reservation) {
        ReservationDTO dto = new ReservationDTO();
        dto.setId(reservation.getId());
        dto.setHotelId(reservation.getHotelId());
        dto.setPersonId(reservation.getPersonId());
        dto.setName(reservation.getName());
        dto.setLastName(reservation.getLastName());
        dto.setPersonBalance(reservation.getPersonBalance());
        return dto;
    }
}
