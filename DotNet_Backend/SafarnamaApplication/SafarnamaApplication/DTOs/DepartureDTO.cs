namespace SafarnamaApplication.DTOs
{
    public class DepartureDTO
    {
        public int DepartureId { get; set; }
        public int? PackageId { get; set; }
        public DateOnly? DepartDate { get; set; }
        public DateOnly? EndDate { get; set; }
        public int? NoOfDays { get; set; }
    }
}
