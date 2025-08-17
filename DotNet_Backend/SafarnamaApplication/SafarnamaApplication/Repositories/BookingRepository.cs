using Microsoft.EntityFrameworkCore;
using SafarnamaApplication.Models;

namespace SafarnamaApplication.Repositories
{
    public class BookingRepository : IBookingRepository
    {
        private readonly SafarnamadbContext _context;

        public BookingRepository(SafarnamadbContext context)
        {
            _context = context;
        }

        public async Task<IEnumerable<Booking>> GetAll()
        {
            return await _context.Bookings
                .Include(b => b.Passengers)
                .Include(b => b.Package)
                .Include(b => b.Departure)
                .Include(b => b.Cust)
                .ToListAsync();
        }

        public async Task<Booking?> GetById(int id)
        {
            return await _context.Bookings
                .Include(b => b.Package)
                .Include(b => b.Departure)
                .Include(b => b.Cust)
                .FirstOrDefaultAsync(b => b.BookingId == id);
        }

        public async Task<Booking?> GetByIdWithPassengers(int id)
        {
            return await _context.Bookings
                .Include(b => b.Passengers)
                .FirstOrDefaultAsync(b => b.BookingId == id);
        }

        public async Task Add(Booking booking)
        {
            await _context.Bookings.AddAsync(booking);
        }

        public Task Update(Booking booking)
        {
            _context.Bookings.Update(booking);
            return Task.CompletedTask;
        }

        public async Task Delete(int id)
        {
            var booking = await GetById(id);
            if (booking != null) _context.Bookings.Remove(booking);
        }

        public async Task Save()
        {
            await _context.SaveChangesAsync();
        }
    }
}
