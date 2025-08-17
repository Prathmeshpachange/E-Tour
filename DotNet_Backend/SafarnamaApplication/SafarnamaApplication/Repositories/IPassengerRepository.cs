using SafarnamaApplication.Models;

namespace SafarnamaApplication.Repositories
{
    public interface IPassengerRepository
    {
        IEnumerable<Passenger> GetAll();
        Passenger? GetById(int id);
        IEnumerable<Passenger> GetByBookingId(int bookingId);
        void Add(Passenger passenger);
        void Update(Passenger passenger);
        void Delete(int id);
        void Save();
    }
}
