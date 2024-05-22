package com.example.hotel;

import com.example.hotel.entity.Hotel;
import com.example.hotel.entity.Person;
import com.example.hotel.repository.HotelRepository;
import com.example.hotel.repository.PersonRepository;
import com.example.hotel.services.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ReservationServiceTest {

	@Mock
	private PersonRepository personRepository;

	@Mock
	private HotelRepository hotelRepository;

	@InjectMocks
	private ReservationService reservationService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testReservationSuccessful() {
		Person person = new Person();
		person.setPersonBalance(100);
		when(personRepository.findByUsername("user")).thenReturn(Optional.of(person));

		Hotel hotel = new Hotel();
		hotel.setRoomPrice(50);
		hotel.setHotelCapacity(1);
		when(hotelRepository.findByHotelName("hotel")).thenReturn(Optional.of(hotel));

		String result = reservationService.makeReservation("user", "hotel");

		assertEquals("Reservation successful", result);
		assertEquals(50, person.getPersonBalance());
		assertEquals(0, hotel.getHotelCapacity());
		verify(personRepository, times(1)).save(person);
		verify(hotelRepository, times(1)).save(hotel);
	}

	@Test
	void testNotEnoughBalance() {
		Person person = new Person();
		person.setPersonBalance(40); // Less than the room price
		when(personRepository.findByUsername("user")).thenReturn(Optional.of(person));

		Hotel hotel = new Hotel();
		hotel.setRoomPrice(50);
		when(hotelRepository.findByHotelName("hotel")).thenReturn(Optional.of(hotel));

		String result = reservationService.makeReservation("user", "hotel");

		assertEquals("Not enough balance", result);
		verify(personRepository, never()).save(any());
		verify(hotelRepository, never()).save(any());
	}

	@Test
	void testNotEnoughCapacity() {
		Person person = new Person();
		person.setPersonBalance(100);
		when(personRepository.findByUsername("user")).thenReturn(Optional.of(person));

		Hotel hotel = new Hotel();
		hotel.setRoomPrice(50);
		hotel.setHotelCapacity(0); // No available rooms
		when(hotelRepository.findByHotelName("hotel")).thenReturn(Optional.of(hotel));

		String result = reservationService.makeReservation("user", "hotel");

		assertEquals("Not enough capacity", result);
		verify(personRepository, never()).save(any());
		verify(hotelRepository, never()).save(any());
	}

	@Test
	void testPersonOrHotelNotFound() {
		when(personRepository.findByUsername("user")).thenReturn(Optional.empty());
		when(hotelRepository.findByHotelName("hotel")).thenReturn(Optional.empty());

		String result = reservationService.makeReservation("user", "hotel");

		assertEquals("Person or Hotel not found", result);
		verify(personRepository, never()).save(any());
		verify(hotelRepository, never()).save(any());
	}
}
