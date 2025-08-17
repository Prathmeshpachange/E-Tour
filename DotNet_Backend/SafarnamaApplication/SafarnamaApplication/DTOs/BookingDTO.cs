namespace SafarnamaApplication.DTOs
{
    public class BookingDTO
    {
        public int BookingId { get; set; }
        public DateTime BookingDate { get; set; }
        public int NoOfPax { get; set; }
        public double TourAmount { get; set; }
        public double Taxes { get; set; }
        public double TotalAmount { get; set; }
        public string? PaymentStatus { get; set; }
        public int? CustomerId { get; set; }
        public int? PackageId { get; set; }
        public int? DepartureId { get; set; }
        public List<PassengerDTO>? Passengers { get; set; }
    }
}
