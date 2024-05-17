package com.example.hotel.classes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class Person {
    @Id
    private Long id;
    @Column
    public  static  String  name;
    public  static  String  last_name;
    public static  double person_balance;



}
