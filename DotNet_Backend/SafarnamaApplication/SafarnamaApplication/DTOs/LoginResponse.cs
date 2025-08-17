namespace SafarnamaApplication.DTOs
{
    public class LoginResponse
    {
        public string Token { get; set; } = null!;
        public int CustId { get; set; }
        public string? CustName { get; set; }
        public string? CustEmail { get; set; }
    }
}
