package service.impl;

import dto.BookingDTO;
import entity.Booking;
import entity.Customer;
import entity.Departure;
import entity.TourPackage;
import mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.BookingRepository;
import service.BookingService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        Booking booking = Mapper.MapToBooking(bookingDTO);
        Booking savedBooking = bookingRepository.save(booking);
        return Mapper.MapToBookingDTO(savedBooking);
    }

    @Override
    public BookingDTO getBookingById(Integer bookingId) {
        Optional<Booking> optional = bookingRepository.findById(bookingId);
        return optional.map(Mapper::MapToBookingDTO).orElse(null);
    }

    @Override
    public List<BookingDTO> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        List<BookingDTO> dtos = new ArrayList<>();

        for (Booking booking : bookings) {
            dtos.add(Mapper.MapToBookingDTO(booking));
        }

        return dtos;
    }

    @Override
    public BookingDTO updateBooking(Integer id, BookingDTO bookingDTO) {
        Optional<Booking> optional = bookingRepository.findById(id);

        if (optional.isPresent()) {
            Booking existing = optional.get();

            existing.setBookingDate(bookingDTO.getBookingDate());
            existing.setNoOfPax(bookingDTO.getNoOfPax());
            existing.setTourAmount(bookingDTO.getTourAmount());
            existing.setTaxes(bookingDTO.getTaxes());
            existing.setTotalAmount(bookingDTO.getTotalAmount());
            existing.setPaymentStatus(bookingDTO.getPaymentStatus());

            if (bookingDTO.getCustomerId() != null) {
                Customer customer = new Customer();
                customer.setCustId(bookingDTO.getCustomerId());
                existing.setCustomer(customer);
            }

            if (bookingDTO.getPackageId() != null) {
                TourPackage tourPackage = new TourPackage();
                tourPackage.setPackageId(bookingDTO.getPackageId());
                existing.setTourPackage(tourPackage);
            }

            if (bookingDTO.getDepartureId() != null) {
                Departure departure = new Departure();
                departure.setDepartureId(bookingDTO.getDepartureId());
                existing.setDeparture(departure);
            }

            Booking updated = bookingRepository.save(existing);
            return Mapper.MapToBookingDTO(updated);
        }

        return null;
    }

    @Override
    public void deleteBooking(Integer id) {
        bookingRepository.deleteById(id);
    }
    
    @Override
    public BookingDTO getBookingWithPassengers(Integer bookingId) {
        Booking booking = bookingRepository.findByIdWithPassengers(bookingId);
        return Mapper.MapToBookingDTO(booking);
    }

    @Override
    public boolean updatePaymentStatus(Integer bookingId, String status) {
        Optional<Booking> bookingOpt = bookingRepository.findById(bookingId);
        if (bookingOpt.isPresent()) {
            Booking booking = bookingOpt.get();
            booking.setPaymentStatus(status);
            bookingRepository.save(booking);
            return true;
        }
        return false;
    }
}
