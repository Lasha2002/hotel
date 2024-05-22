package com.example.hotel.reservationDTO;
import lombok.Data;
@Data
public class ReservationDTO {

    private Long id;
    private String hotelId;
    private String personId;
    private String name;
    private String lastName;
    private double personBalance;
}
