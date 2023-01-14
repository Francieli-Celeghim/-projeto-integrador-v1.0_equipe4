package com.dh.projetointegradorv1._equipe4.dh_carshop.controller;

import com.dh.projetointegradorv1._equipe4.dh_carshop.dto.BookingDto;
import com.dh.projetointegradorv1._equipe4.dh_carshop.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BookingDto> createBooking(@RequestBody BookingDto dto) {
        return ResponseEntity.status(201).body(bookingService.createBooking(dto));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<BookingDto>> listAllBookings() {
        return ResponseEntity.ok(bookingService.listAllBookings());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BookingDto> findBookingById(@PathVariable(value = "id") Integer id) {
        BookingDto dto = bookingService.findBookingById(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(params = "userId")
    public ResponseEntity<List<BookingDto>> findProductByUser(@RequestParam Integer userId) {
        return ResponseEntity.ok(bookingService.findBookingByUser(userId));
    }

}
