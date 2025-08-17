package service;

import dto.BookingDTO;
import java.util.List;

public interface BookingService {
    BookingDTO createBooking(BookingDTO bookingDTO);
    BookingDTO getBookingById(Integer bookingId);
    List<BookingDTO> getAllBookings();
    BookingDTO updateBooking(Integer bookingId, BookingDTO bookingDTO);
    void deleteBooking(Integer bookingId);
    BookingDTO getBookingWithPassengers(Integer bookingId);
    boolean updatePaymentStatus(Integer bookingId, String status);
    
}
