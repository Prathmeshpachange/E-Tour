
using SafarnamaApplication.DTOs;

namespace SafarnamaApplication.DTOs
{
    public class PackageDTO
    {
        public int PackageId { get; set; }
        public int? SubcategoryId { get; set; }
        public int? CategoryId { get; set; }

        public string? PackageName { get; set; }
        public string? PackageInfo { get; set; }
        public string? PackageImagePath { get; set; }
        public int? DurationDays { get; set; }
        public DateOnly? StartDate { get; set; }
        public DateOnly? EndDate { get; set; }

        public string? CategoryName { get; set; }
        public string? SubcategoryName { get; set; }

        public List<CostDTO> Costs { get; set; } = new();
        public List<ItineraryDTO> Itineraries { get; set; } = new();
        public List<DepartureDTO> Departures { get; set; } = new();


    }
}
