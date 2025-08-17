package controller;

import dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.BookingService;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins="*")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingDTO> create(@RequestBody BookingDTO bookingDTO) {
        return ResponseEntity.ok(bookingService.createBooking(bookingDTO));
    }

    @GetMapping("/{id}")
    
    public ResponseEntity<BookingDTO> get(@PathVariable Integer id) {
        BookingDTO dto = bookingService.getBookingById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
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
    
    @GetMapping("/{id}/passengers")
    public ResponseEntity<List<PassengerDTO>> getPassengersForBooking(@PathVariable Integer id) {
        BookingDTO dto = bookingService.getBookingWithPassengers(id);
       
        if (dto == null || dto.getPassengers() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto.getPassengers());
    }
    
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Controller is working");
    }

    @PutMapping("/{id}/payment")
    public ResponseEntity<String> updatePaymentStatus(@PathVariable Integer id, @RequestParam String status) {
        boolean updated = bookingService.updatePaymentStatus(id, status);
        if (updated) {
            return ResponseEntity.ok("Payment status updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
