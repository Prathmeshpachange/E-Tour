using SafarnamaApplication.Models;

namespace SafarnamaApplication.Repositories
{
    public interface ICustomerRepository
    {
        Task<IEnumerable<Customer>> GetAllAsync();
        Task<Customer?> GetByIdAsync(int id);
        Task<Customer> AddAsync(Customer customer);
        Task<Customer> UpdateAsync(Customer customer);
        Task<bool> DeleteAsync(int id);

        Task<Customer?> GetByEmailAsync(string email);

    }
}
