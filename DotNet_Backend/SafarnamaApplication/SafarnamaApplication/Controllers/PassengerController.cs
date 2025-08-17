using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using SafarnamaApplication.DTOs;
using SafarnamaApplication.Services;

namespace SafarnamaApplication.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class PassengerController : ControllerBase
    {
        private readonly IPassengerService _service;

        public PassengerController(IPassengerService service)
        {
            _service = service;
        }

        [HttpGet]
        public IActionResult GetAll()
        {
            return Ok(_service.GetAllPassengers());
        }

        [HttpGet("{id}")]
        public IActionResult GetById(int id)
        {
            var passenger = _service.GetPassengerById(id);
            if (passenger == null) return NotFound();
            return Ok(passenger);
        }

        [HttpGet("bybooking/{bookingId}")]
        public IActionResult GetByBookingId(int bookingId)
        {
            return Ok(_service.GetPassengersByBookingId(bookingId));
        }

        [HttpPost]
        public IActionResult Create(PassengerDTO dto)
        {
            _service.AddPassenger(dto);
            return Ok("Passenger added successfully");
        }

        [HttpPut("{id}")]
        public IActionResult Update(int id, PassengerDTO dto)
        {
            if (id != dto.PaxId) return BadRequest();
            _service.UpdatePassenger(dto);
            return Ok("Passenger updated successfully");
        }

        [HttpDelete("{id}")]
        public IActionResult Delete(int id)
        {
            _service.DeletePassenger(id);
            return Ok("Passenger deleted successfully");
        }
    }
}
