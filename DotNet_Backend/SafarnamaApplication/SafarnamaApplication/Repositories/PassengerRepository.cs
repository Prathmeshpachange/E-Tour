using SafarnamaApplication.Models;
using Microsoft.EntityFrameworkCore;

namespace SafarnamaApplication.Repositories
{
    public class PassengerRepository : IPassengerRepository
    {
        private readonly SafarnamadbContext _context;

        public PassengerRepository(SafarnamadbContext context)
        {
            _context = context;
        }

        public IEnumerable<Passenger> GetAll()
        {
            return _context.Passengers
                           .Include(p => p.Booking)
                           .ToList();
        }

        public Passenger? GetById(int id)
        {
            return _context.Passengers
                           .Include(p => p.Booking)
                           .FirstOrDefault(p => p.PaxId == id);
        }

        public IEnumerable<Passenger> GetByBookingId(int bookingId)
        {
            return _context.Passengers
                           .Where(p => p.BookingId == bookingId)
                           .ToList();
        }

        public void Add(Passenger passenger) => _context.Passengers.Add(passenger);

        public void Update(Passenger passenger) => _context.Passengers.Update(passenger);

        public void Delete(int id)
        {
            var passenger = GetById(id);
            if (passenger != null)
                _context.Passengers.Remove(passenger);
        }

        public void Save() => _context.SaveChanges();
    }
}
