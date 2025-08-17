using AutoMapper;
using SafarnamaApplication.DTOs;
using SafarnamaApplication.Models;
using SafarnamaApplication.Repositories;

namespace SafarnamaApplication.Services
{
    public class BookingService : IBookingService
    {
        private readonly IBookingRepository _bookingRepository;
        private readonly IMapper _mapper;

        public BookingService(IBookingRepository bookingRepo, IMapper mapper)
        {
            _bookingRepository = bookingRepo;
            _mapper = mapper;
        }

        public async Task<BookingDTO> CreateBooking(BookingDTO bookingDTO)
        {
            var booking = _mapper.Map<Booking>(bookingDTO);

            // ✅ Link passengers to this booking (like Spring Boot)
            if (booking.Passengers != null && booking.Passengers.Any())
            {
                foreach (var passenger in booking.Passengers)
                {
                    passenger.Booking = booking;
                }
            }

            await _bookingRepository.Add(booking);
            await _bookingRepository.Save();

            return _mapper.Map<BookingDTO>(booking);
        }

        public async Task<BookingDTO?> GetBookingById(int bookingId)
        {
            var booking = await _bookingRepository.GetById(bookingId);
            return booking == null ? null : _mapper.Map<BookingDTO>(booking);
        }

        public async Task<IEnumerable<BookingDTO>> GetAllBookings()
        {
            var bookings = await _bookingRepository.GetAll();
            return _mapper.Map<IEnumerable<BookingDTO>>(bookings);
        }

        public async Task<BookingDTO?> UpdateBooking(int bookingId, BookingDTO bookingDTO)
        {
            var existing = await _bookingRepository.GetById(bookingId);
            if (existing == null) return null;

            // Update simple fields
            existing.BookingDate = bookingDTO.BookingDate;
            existing.NoOfPax = bookingDTO.NoOfPax;
            existing.TourAmount = bookingDTO.TourAmount;
            existing.Taxes = bookingDTO.Taxes;
            existing.TotalAmount = bookingDTO.TotalAmount;
            existing.PaymentStatus = bookingDTO.PaymentStatus;

            // Update foreign keys
            if (bookingDTO.CustomerId.HasValue)
                existing.CustId = bookingDTO.CustomerId.Value;

            if (bookingDTO.PackageId.HasValue)
                existing.PackageId = bookingDTO.PackageId.Value;

            if (bookingDTO.DepartureId.HasValue)
                existing.DepartureId = bookingDTO.DepartureId.Value;

            // ✅ Update passengers if provided
            if (bookingDTO.Passengers != null && bookingDTO.Passengers.Any())
            {
                existing.Passengers.Clear();
                foreach (var passengerDto in bookingDTO.Passengers)
                {
                    var passenger = _mapper.Map<Passenger>(passengerDto);
                    passenger.Booking = existing;
                    existing.Passengers.Add(passenger);
                }
            }

            await _bookingRepository.Update(existing);
            await _bookingRepository.Save();

            return _mapper.Map<BookingDTO>(existing);
        }

        public async Task DeleteBooking(int bookingId)
        {
            await _bookingRepository.Delete(bookingId);
            await _bookingRepository.Save();
        }

        public async Task<BookingDTO?> GetBookingWithPassengers(int bookingId)
        {
            var booking = await _bookingRepository.GetByIdWithPassengers(bookingId);
            return booking == null ? null : _mapper.Map<BookingDTO>(booking);
        }

        public async Task<bool> UpdatePaymentStatus(int bookingId, string status)
        {
            var booking = await _bookingRepository.GetById(bookingId);
            if (booking == null) return false;

            booking.PaymentStatus = status;
            await _bookingRepository.Update(booking);
            await _bookingRepository.Save();
            return true;
        }
    }
}
