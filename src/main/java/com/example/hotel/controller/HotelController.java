package com.example.hotel.controller;
import com.example.hotel.hotelDTO.HotelDTO;
import com.example.hotel.hotelDTO.HotelResponseDTO;
import com.example.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/hotels")
public class HotelController
{
    @Autowired
    private HotelService hotelService;

    @PostMapping
    public HotelDTO createHotel(@RequestBody HotelDTO hotelDTO) {
        return hotelService.saveHotel(hotelDTO);
    }

    @GetMapping("get/{id}")
    public HotelDTO getHotelById(@PathVariable Long id) {
        return hotelService.getHotelById(id);
    }

    @GetMapping("/all")
    public List<HotelResponseDTO> getAllHotels() {
        return hotelService.findAllHotels();
    }

    @DeleteMapping("delete/{id}")
    public void deleteHotelById(@PathVariable long id) {
        hotelService.deleteHotelById(id);
    }
}
