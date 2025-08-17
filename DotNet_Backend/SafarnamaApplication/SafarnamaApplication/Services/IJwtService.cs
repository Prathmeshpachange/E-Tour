using SafarnamaApplication.Models;

namespace SafarnamaApplication.Services
{
    public interface IJwtService
    {
        string GenerateToken(Customer customer);
    }
}
