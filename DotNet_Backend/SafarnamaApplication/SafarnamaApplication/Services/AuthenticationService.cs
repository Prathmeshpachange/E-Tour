using BCrypt.Net;
using SafarnamaApplication.DTOs;
using SafarnamaApplication.Models;
using SafarnamaApplication.Repositories;

namespace SafarnamaApplication.Services
{
    public class AuthenticationService : IAuthenticationService
    {
        private readonly IAuthenticationRepository _authRepo;
        private readonly ICustomerRepository _customerRepo;
        private readonly IJwtService _jwtService;

        public AuthenticationService(
            IAuthenticationRepository authRepo,
            ICustomerRepository customerRepo,
            IJwtService jwtService)
        {
            _authRepo = authRepo;
            _customerRepo = customerRepo;
            _jwtService = jwtService;
        }

        public async Task<LoginResponse?> AuthenticateUserAsync(LoginRequest request)
        {
            var auth = await _authRepo.GetByEmailAsync(request.Email);
            if (auth == null) return null;

            if (!BCrypt.Net.BCrypt.Verify(request.Password, auth.Password ?? string.Empty))
            {
                return null;
            }

            // update last login
            auth.LastLogin = DateTime.Now;
            await _authRepo.UpdateAsync(auth);

            var customer = await _customerRepo.GetByIdAsync(auth.CustId!.Value);
            if (customer == null) return null;

            var token = _jwtService.GenerateToken(customer);

            return new LoginResponse
            {
                Token = token,
                CustId = customer.CustId,
                CustName = customer.CustName,
                CustEmail = customer.CustEmail
            };
        }

        public async Task<SignupResponse> RegisterUserAsync(SignupRequest request)
        {
            // check email uniqueness
            var existing = await _customerRepo.GetByEmailAsync(request.Email);
            if (existing != null)
            {
                return new SignupResponse
                {
                    CustId = 0,
                    Message = "Email already in use."
                };
            }

            var customer = new Customer
            {
                CustName = request.Name,
                CustEmail = request.Email,
                CustPhone = request.Phone,
                CustAddress = request.Address,
                CustCity = request.City,
                CustState = request.State,
                CustPincode = request.Pincode,
                CustCountry = "India",
                CustCreatedAt = DateTime.Now
            };

            if (!string.IsNullOrEmpty(request.Dob))
            {
                if (DateTime.TryParse(request.Dob, out var dt))
                {
                    // if your model uses DateOnly
                    try
                    {
                        customer.CustDob = DateOnly.FromDateTime(dt);
                    }
                    catch
                    {
                        // ignore parse if DateOnly not desired
                    }
                }
            }

            var createdCustomer = await _customerRepo.AddAsync(customer);

            // create authentication record with hashed password
            var hash = BCrypt.Net.BCrypt.HashPassword(request.Password);

            var auth = new Authentication
            {
                CustId = createdCustomer.CustId,
                Password = hash,
                LastLogin = null,
                Status = 1
            };

            await _authRepo.AddAsync(auth);

            return new SignupResponse
            {
                CustId = createdCustomer.CustId,
                Message = "Signup successful"
            };
        }
    }
}
