using SafarnamaApplication.DTOs;

namespace SafarnamaApplication.Services
{
    public interface IBookingService
    {
        Task<BookingDTO> CreateBooking(BookingDTO bookingDTO);
        Task<BookingDTO?> GetBookingById(int bookingId);
        Task<IEnumerable<BookingDTO>> GetAllBookings();
        Task<BookingDTO?> UpdateBooking(int bookingId, BookingDTO bookingDTO);
        Task DeleteBooking(int bookingId);
        Task<BookingDTO?> GetBookingWithPassengers(int bookingId);
        Task<bool> UpdatePaymentStatus(int bookingId, string status);
    }   
}
