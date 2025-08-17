using AutoMapper;
using SafarnamaApplication.DTOs;
using SafarnamaApplication.Models;
using SafarnamaApplication.Repositories;

namespace SafarnamaApplication.Services
{
    public class CustomerService : ICustomerService
    {
        private readonly ICustomerRepository _customerRepository;
        private readonly IMapper _mapper;

        public CustomerService(ICustomerRepository customerRepository, IMapper mapper)
        {
            _customerRepository = customerRepository;
            _mapper = mapper;
        }

        public async Task<IEnumerable<CustomerDTO>> GetAllCustomersAsync()
        {
            var customers = await _customerRepository.GetAllAsync();
            return _mapper.Map<IEnumerable<CustomerDTO>>(customers);
        }

        public async Task<CustomerDTO?> GetCustomerByIdAsync(int id)
        {
            var customer = await _customerRepository.GetByIdAsync(id);
            return customer == null ? null : _mapper.Map<CustomerDTO>(customer);
        }

        public async Task<CustomerDTO> CreateCustomerAsync(CustomerDTO customerDto)
        {
            var entity = _mapper.Map<Customer>(customerDto);
            var created = await _customerRepository.AddAsync(entity);
            return _mapper.Map<CustomerDTO>(created);
        }

        public async Task<CustomerDTO?> UpdateCustomerAsync(int id, CustomerDTO customerDto)
        {
            var existing = await _customerRepository.GetByIdAsync(id);
            if (existing == null) return null;

            _mapper.Map(customerDto, existing);
            var updated = await _customerRepository.UpdateAsync(existing);
            return _mapper.Map<CustomerDTO>(updated);
        }

        public async Task<bool> DeleteCustomerAsync(int id)
        {
            return await _customerRepository.DeleteAsync(id);
        }
    }
}
