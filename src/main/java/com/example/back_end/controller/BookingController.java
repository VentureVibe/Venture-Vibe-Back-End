package com.example.back_end.controller;

import com.example.back_end.dto.BookingDTO;
import com.example.back_end.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/booking")
@CrossOrigin("http://localhost:5173")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping("/store")
    public ResponseEntity<BookingDTO> storeBooking(@RequestBody BookingDTO bookingDTO) {
        BookingDTO savedBooking = bookingService.saveBooking(bookingDTO);
        return ResponseEntity.ok(savedBooking);
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookingDTO>> getAllBookings() {
        List<BookingDTO> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Long id) {
        BookingDTO booking = bookingService.getBookingById(id);
        return ResponseEntity.ok(booking);
    }

    @GetMapping("/booker/{bookerId}")
    public ResponseEntity<List<BookingDTO>> getBookingsByBookerId(@PathVariable String bookerId) {
        List<BookingDTO> bookings = bookingService.getBookingsByBookerId(bookerId);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/travelGuide/{travelGuideId}")
    public ResponseEntity<List<BookingDTO>> getBookingsByTravelGuideId(@PathVariable String travelGuideId) {
        List<BookingDTO> bookings = bookingService.getBookingsByTravelGuideId(travelGuideId);
        return ResponseEntity.ok(bookings);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.ok().build();
    }
}
