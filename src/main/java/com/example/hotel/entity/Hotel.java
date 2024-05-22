package com.example.hotel.entity;
import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String hotelName;
    private int numberOfRooms;
    private double rate;
    private   int hotelCapacity;
    private   double roomPrice;
}
