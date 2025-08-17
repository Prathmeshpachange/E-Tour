using Microsoft.EntityFrameworkCore;
using SafarnamaApplication.Models;

namespace SafarnamaApplication.Repositories
{
    public class AuthenticationRepository : IAuthenticationRepository
    {
        private readonly SafarnamadbContext _context;

        public AuthenticationRepository(SafarnamadbContext context)
        {
            _context = context;
        }

        public async Task<Authentication?> GetByCustomerIdAsync(int custId)
        {
            return await _context.Authentications.FirstOrDefaultAsync(a => a.CustId == custId);
        }

        public async Task<Authentication?> GetByEmailAsync(string email)
        {
            var customer = await _context.Customers.FirstOrDefaultAsync(c => c.CustEmail == email);
            if (customer == null) return null;
            return await _context.Authentications.FirstOrDefaultAsync(a => a.CustId == customer.CustId);
        }

        public async Task<Authentication> AddAsync(Authentication auth)
        {
            _context.Authentications.Add(auth);
            await _context.SaveChangesAsync();
            return auth;
        }

        public async Task<Authentication> UpdateAsync(Authentication auth)
        {
            _context.Authentications.Update(auth);
            await _context.SaveChangesAsync();
            return auth;
        }
    }
}
