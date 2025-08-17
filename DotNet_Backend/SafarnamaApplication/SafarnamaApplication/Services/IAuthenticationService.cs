using SafarnamaApplication.DTOs;

namespace SafarnamaApplication.Services
{
    public interface IAuthenticationService
    {
        Task<LoginResponse?> AuthenticateUserAsync(LoginRequest request);
        Task<SignupResponse> RegisterUserAsync(SignupRequest request);
    }
}
