package service;

import dto.PassengerDTO;

import java.util.List;

public interface PassengerService {
    PassengerDTO savePassenger(PassengerDTO passengerDTO);
    PassengerDTO getPassengerById(Integer id);
    List<PassengerDTO> getAllPassengers();
    PassengerDTO updatePassenger(Integer id, PassengerDTO passengerDTO);
    void deletePassenger(Integer id);
}
