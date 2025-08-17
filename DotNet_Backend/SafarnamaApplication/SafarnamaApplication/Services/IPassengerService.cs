using SafarnamaApplication.DTOs;

namespace SafarnamaApplication.Services
{
    public interface IPassengerService
    {
        IEnumerable<PassengerDTO> GetAllPassengers();
        PassengerDTO? GetPassengerById(int id);
        IEnumerable<PassengerDTO> GetPassengersByBookingId(int bookingId);
        void AddPassenger(PassengerDTO dto);
        void UpdatePassenger(PassengerDTO dto);
        void DeletePassenger(int id);
    }
}
