package com.example.hotel.hotelDTO;
import lombok.Data;
@Data
public class HotelResponseDTO {

    private String hotelName;
    private int numberOfRooms;
    private double rate;
    private   int hotelCapacity;
    private   double roomPrice;
}
