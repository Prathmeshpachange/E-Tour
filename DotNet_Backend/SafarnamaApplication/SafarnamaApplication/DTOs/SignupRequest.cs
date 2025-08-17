namespace SafarnamaApplication.DTOs
{
    public class SignupRequest
    {
        public string Name { get; set; } = null!;
        public string Email { get; set; } = null!;
        public string Phone { get; set; } = null!;
        public string? Dob { get; set; }    // ISO date string or empty
        public string Gender { get; set; } = "M";
        public string? Address { get; set; }
        public string? City { get; set; }
        public string? State { get; set; }
        public string? Pincode { get; set; }
        public string Password { get; set; } = null!;
    }
}
