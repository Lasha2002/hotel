package com.example.hotel.services;
import com.example.hotel.entity.Hotel;
import com.example.hotel.hotelDTO.HotelDTO;
import com.example.hotel.hotelDTO.HotelResponseDTO;
import com.example.hotel.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;
    public HotelDTO saveHotel(HotelDTO hotelDTO)
    {
        if (hotelDTO.getId() != null && hotelRepository.existsById(hotelDTO.getId())) {
            throw new RuntimeException("Person with ID " + hotelDTO.getId() + " already exists");
        }
        Hotel hotel = new Hotel();
        hotel.setHotelName(hotelDTO.getHotelName());
        hotel.setHotelCapacity(hotelDTO.getHotelCapacity());
        hotel.setRoomPrice(hotelDTO.getRoomPrice());
        hotel.setRate(hotelDTO.getRate());
        Hotel savedHotel = hotelRepository.save(hotel);
        return convertToDTO(savedHotel);
    }

    private HotelDTO convertToDTO(Hotel hotel) {
        HotelDTO dto = new HotelDTO();
        dto.setId(hotel.getId());
        dto.setHotelName(hotel.getHotelName());
        dto.setHotelCapacity(hotel.getHotelCapacity());
        dto.setRate(hotel.getRate());
        dto.setRoomPrice(hotel.getRoomPrice());
        return dto;
    }
    public HotelDTO getHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));
        return convertToGetHotelTO(hotel) ;
    }
    private HotelDTO convertToGetHotelTO(Hotel hotel) {
        HotelDTO hotelDTO= new HotelDTO();
        hotelDTO.setRoomPrice(hotel.getRoomPrice());
        hotelDTO.setHotelCapacity(hotel.getHotelCapacity());
        hotelDTO.setNumberOfRooms(hotel.getNumberOfRooms());
        hotelDTO.setHotelName(hotel.getHotelName());
        hotelDTO.setId(hotel.getId());
        hotelDTO.setRate(hotel.getRate());
        return hotelDTO;
    }
    public List<HotelResponseDTO> findAllHotels() {
        List<Hotel> hotels = hotelRepository.findAll();
        return hotels.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }
    private HotelResponseDTO convertToResponseDTO(Hotel hotel) {
        HotelResponseDTO dto = new HotelResponseDTO();
        dto.setHotelName(hotel.getHotelName());
        dto.setRate(hotel.getRate());
        dto.setNumberOfRooms(hotel.getNumberOfRooms());
        dto.setRoomPrice(hotel.getRoomPrice());
        dto.setHotelCapacity(hotel.getHotelCapacity());
        return dto;
    }
    public void deleteHotelById(Long id) {
        hotelRepository.deleteById(id);
    }
}