package com.example.hotel.repository;
import com.example.hotel.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface HotelRepository  extends JpaRepository<Hotel,Long> {
    Optional<Hotel> findByHotelName(String hotelName);
    Optional<Hotel>getHotelById(Long id);
}
