using SafarnamaApplication.DTOs;

namespace SafarnamaApplication.Services
{
    public interface ICustomerService
    {
        Task<IEnumerable<CustomerDTO>> GetAllCustomersAsync();
        Task<CustomerDTO?> GetCustomerByIdAsync(int id);
        Task<CustomerDTO> CreateCustomerAsync(CustomerDTO customerDto);
        Task<CustomerDTO?> UpdateCustomerAsync(int id, CustomerDTO customerDto);
        Task<bool> DeleteCustomerAsync(int id);
    }
}
