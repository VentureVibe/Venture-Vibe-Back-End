package com.example.back_end.service;

import com.example.back_end.dto.BookingDTO;
import com.example.back_end.model.Booking;
import com.example.back_end.repository.BookingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ModelMapper modelMapper;

    public BookingDTO saveBooking(BookingDTO bookingDTO) {
        Booking booking = modelMapper.map(bookingDTO, Booking.class);
        booking = bookingRepository.save(booking);
        return modelMapper.map(booking, BookingDTO.class);
    }

    public List<BookingDTO> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(booking -> modelMapper.map(booking, BookingDTO.class))
                .collect(Collectors.toList());
    }

    public BookingDTO getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));
        return modelMapper.map(booking, BookingDTO.class);
    }

    public List<BookingDTO> getBookingsByBookerId(String bookerId) {
        return bookingRepository.findByBookerId(bookerId).stream()
                .map(booking -> modelMapper.map(booking, BookingDTO.class))
                .collect(Collectors.toList());
    }

    public List<BookingDTO> getBookingsByTravelGuideId(String travelGuideId) {
        return bookingRepository.findByTravelGuideId(travelGuideId).stream()
                .map(booking -> modelMapper.map(booking, BookingDTO.class))
                .collect(Collectors.toList());
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}
