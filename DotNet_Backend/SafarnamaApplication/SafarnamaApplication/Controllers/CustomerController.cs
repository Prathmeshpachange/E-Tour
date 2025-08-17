using Microsoft.AspNetCore.Mvc;
using SafarnamaApplication.DTOs;
using SafarnamaApplication.Services;

namespace SafarnamaApplication.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class CustomerController : ControllerBase
    {
        private readonly ICustomerService _customerService;

        public CustomerController(ICustomerService customerService)
        {
            _customerService = customerService;
        }

        [HttpPost]
        public async Task<ActionResult<CustomerDTO>> Create(CustomerDTO customerDto)
        {
            var created = await _customerService.CreateCustomerAsync(customerDto);
            return Ok(created);
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<CustomerDTO>> Get(int id)
        {
            var customer = await _customerService.GetCustomerByIdAsync(id);
            if (customer == null) return NotFound();
            return Ok(customer);
        }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<CustomerDTO>>> GetAll()
        {
            return Ok(await _customerService.GetAllCustomersAsync());
        }

        [HttpPut("{id}")]
        public async Task<ActionResult<CustomerDTO>> Update(int id, CustomerDTO customerDto)
        {
            var updated = await _customerService.UpdateCustomerAsync(id, customerDto);
            if (updated == null) return NotFound();
            return Ok(updated);
        }

        [HttpDelete("{id}")]
        public async Task<IActionResult> Delete(int id)
        {
            var deleted = await _customerService.DeleteCustomerAsync(id);
            if (!deleted) return NotFound();
            return Ok("Customer deleted successfully");
        }
    }
}
