package com.example.hotel.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    private Long id;
    private String hotelId;
    private String personId;
    private String name;
    private String lastName;
    private double personBalance;
}
