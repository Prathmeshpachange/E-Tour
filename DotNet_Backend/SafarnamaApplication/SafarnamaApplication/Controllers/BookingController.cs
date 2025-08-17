using Microsoft.AspNetCore.Mvc;
using SafarnamaApplication.DTOs;
using SafarnamaApplication.Services;

namespace SafarnamaApplication.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class BookingController : ControllerBase
    {
        private readonly IBookingService _bookingService;
        private readonly EmailServiceClient _emailServiceClient;
        private readonly CustomerService _customerService;

        public BookingController(IBookingService bookingService,    EmailServiceClient emailServiceClient, CustomerService customerService)
        {
            _bookingService = bookingService;
            _emailServiceClient = emailServiceClient;
            _customerService = customerService;
        }

        [HttpPost]
        public async Task<ActionResult<BookingDTO>> Create([FromBody] BookingDTO bookingDTO)
        {
            var created = await _bookingService.CreateBooking(bookingDTO);
            return Ok(created);
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<BookingDTO>> Get(int id)
        {
            var booking = await _bookingService.GetBookingById(id);
            if (booking == null) return NotFound();
            return Ok(booking);
        }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<BookingDTO>>> GetAll()
        {
            return Ok(await _bookingService.GetAllBookings());
        }

        [HttpPut("{id}")]
        public async Task<ActionResult<BookingDTO>> Update(int id, [FromBody] BookingDTO bookingDTO)
        {
            var updated = await _bookingService.UpdateBooking(id, bookingDTO);
            if (updated == null) return NotFound();
            return Ok(updated);
        }

        [HttpDelete("{id}")]
        public async Task<ActionResult> Delete(int id)
        {
            await _bookingService.DeleteBooking(id);
            return Ok("Booking deleted successfully");
        }

        [HttpGet("{id}/passengers")]
        public async Task<ActionResult<IEnumerable<PassengerDTO>>> GetPassengersForBooking(int id)
        {
            var dto = await _bookingService.GetBookingWithPassengers(id);
            if (dto == null || dto.Passengers == null) return NotFound();
            return Ok(dto.Passengers);
        }

        [HttpGet("test")]
        public ActionResult<string> Test()
        {
            return Ok("Controller is working");
        }

        //[HttpPut("{id}/payment")]
        //public async Task<ActionResult<string>> UpdatePaymentStatus(int id, [FromQuery] string status)
        //{
        //    var updated = await _bookingService.UpdatePaymentStatus(id, status);
        //    if (updated) return Ok("Payment status updated successfully");
        //    return NotFound();
        //}

        [HttpPut("{id}/payment")]
        public async Task<ActionResult<string>> UpdatePaymentStatus(int id, [FromQuery] string status)
        {
            var updated = await _bookingService.UpdatePaymentStatus(id, status);
            if (!updated) return NotFound();

            // 1. Get booking details
            var booking = await _bookingService.GetBookingById(id);
            if (booking == null) return NotFound();

            if (booking.CustomerId == null)
                return BadRequest("Booking does not have a customer linked.");

            // 2. Fetch customer details from CustomerService (or repository)
            var customer = await _customerService.GetCustomerByIdAsync(booking.CustomerId.Value);
            if (customer == null) return NotFound("Customer not found");

            // 3. Use customer info for the email
            var customerEmail = customer.CustEmail; // from CustomerDTO
            var subject = "Payment Confirmation";
            var body = $"Dear {customer.CustName}, your payment for booking #{booking.BookingId} was successful.";

            // 4. Call your Spring Boot email microservice
            await _emailServiceClient.SendEmailAsync(customerEmail, subject, body);

            return Ok("Payment status updated successfully, confirmation email sent to customer.");
        }


    }

}
