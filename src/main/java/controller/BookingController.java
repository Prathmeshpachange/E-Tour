package controller;

import dto.BookingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.BookingService;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingDTO> create(@RequestBody BookingDTO bookingDTO) {
        return ResponseEntity.ok(bookingService.createBooking(bookingDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> get(@PathVariable Integer id) {
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }

    @GetMapping
    public ResponseEntity<List<BookingDTO>> getAll() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingDTO> update(@PathVariable Integer id, @RequestBody BookingDTO bookingDTO) {
        return ResponseEntity.ok(bookingService.updateBooking(id, bookingDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.ok("Booking deleted successfully");
    }
}
