using SafarnamaApplication.Models;

namespace SafarnamaApplication.Repositories
{
    public interface IAuthenticationRepository
    {
        Task<Authentication?> GetByCustomerIdAsync(int custId);
        Task<Authentication?> GetByEmailAsync(string email); // finds customer by email then auth
        Task<Authentication> AddAsync(Authentication auth);
        Task<Authentication> UpdateAsync(Authentication auth);
    }
}
