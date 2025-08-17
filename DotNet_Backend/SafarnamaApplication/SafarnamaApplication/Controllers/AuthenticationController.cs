using Microsoft.AspNetCore.Mvc;
using SafarnamaApplication.DTOs;
using SafarnamaApplication.Services;

namespace SafarnamaApplication.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class AuthController : ControllerBase
    {
        private readonly IAuthenticationService _authService;

        public AuthController(IAuthenticationService authService)
        {
            _authService = authService;
        }

        [HttpPost("login")]
        public async Task<IActionResult> Login([FromBody] LoginRequest request)
        {
            var resp = await _authService.AuthenticateUserAsync(request);
            if (resp == null) return Unauthorized(new { message = "Invalid credentials" });
            return Ok(resp);
        }

        [HttpPost("signup")]
        public async Task<IActionResult> Signup([FromBody] SignupRequest request)
        {
            var resp = await _authService.RegisterUserAsync(request);
            if (resp.CustId == 0) return Conflict(new { message = resp.Message });
            return Ok(resp);
        }
    }
}
