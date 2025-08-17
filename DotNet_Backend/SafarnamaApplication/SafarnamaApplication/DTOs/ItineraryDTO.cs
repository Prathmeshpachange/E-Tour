namespace SafarnamaApplication.DTOs
{
    public class ItineraryDTO
    {
        public int ItineraryId { get; set; }
        public int? PackageId { get; set; }
        public int? DayNo { get; set; }
        public string? DayDetail { get; set; }
    }
}
