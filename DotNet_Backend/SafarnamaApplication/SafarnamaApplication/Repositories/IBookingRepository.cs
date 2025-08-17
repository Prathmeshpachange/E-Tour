using SafarnamaApplication.Models;

namespace SafarnamaApplication.Repositories
{
    public interface IBookingRepository
    {
        Task<IEnumerable<Booking>> GetAll();
        Task<Booking?> GetById(int id);
        Task<Booking?> GetByIdWithPassengers(int id);
        Task Add(Booking booking);
        Task Update(Booking booking);
        Task Delete(int id);
        Task Save();
    }
}
