package service.impl;

import dto.PassengerDTO;
import entity.Booking;
import entity.Passenger;
import mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.BookingRepository;
import repository.PassengerRepository;
import service.PassengerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public PassengerDTO savePassenger(PassengerDTO passengerDTO) {
        Booking booking = bookingRepository.findById(passengerDTO.getBookingId()).orElse(null);
        Passenger passenger = Mapper.MapToPassenger(passengerDTO, booking);
        Passenger saved = passengerRepository.save(passenger);
        return Mapper.MapToPassengerDTO(saved);
    }

    @Override
    public PassengerDTO getPassengerById(Integer id) {
        Optional<Passenger> passenger = passengerRepository.findById(id);
        return passenger.map(Mapper::MapToPassengerDTO).orElse(null);
    }

    @Override
    public List<PassengerDTO> getAllPassengers() {
        List<Passenger> passengers = passengerRepository.findAll();
        List<PassengerDTO> dtos = new ArrayList<>();

        for (Passenger p : passengers) {
            dtos.add(Mapper.MapToPassengerDTO(p));
        }

        return dtos;
    }

    @Override
    public PassengerDTO updatePassenger(Integer id, PassengerDTO passengerDTO) {
        Optional<Passenger> optionalPassenger = passengerRepository.findById(id);
        if (optionalPassenger.isPresent()) {
            Passenger existing = optionalPassenger.get();
            Booking booking = bookingRepository.findById(passengerDTO.getBookingId()).orElse(null);

            existing.setPaxName(passengerDTO.getPaxName());
            existing.setPaxBirthdate(passengerDTO.getPaxBirthdate());
            existing.setPaxType(passengerDTO.getPaxType());
            existing.setPaxAmount(passengerDTO.getPaxAmount());
            existing.setBooking(booking);

            Passenger updated = passengerRepository.save(existing);
            return Mapper.MapToPassengerDTO(updated);
        }

        return null;
    }

    @Override
    public void deletePassenger(Integer id) {
        passengerRepository.deleteById(id);
    }
}
