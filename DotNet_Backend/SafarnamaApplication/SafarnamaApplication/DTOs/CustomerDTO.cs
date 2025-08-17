namespace SafarnamaApplication.DTOs
{
    public class CustomerDTO
    {
        public int CustId { get; set; }
        public string? CustName { get; set; }
        public string? CustEmail { get; set; }
        public string? CustPhone { get; set; }
        public DateOnly? CustDob { get; set; }
        public string? CustGender { get; set; }
        public string? CustAddress { get; set; }
        public string? CustCity { get; set; }
        public string? CustState { get; set; }
        public string? CustPincode { get; set; }
        public string? CustCountry { get; set; }
        public DateTime? CustCreatedAt { get; set; }
    }
}
