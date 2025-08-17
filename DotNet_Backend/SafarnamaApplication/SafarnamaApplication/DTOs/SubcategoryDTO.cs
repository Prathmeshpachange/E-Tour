namespace SafarnamaApplication.DTOs
{
    public class SubcategoryDTO
    {
        public int SubcategoryId { get; set; }
        public string SubcategoryCode { get; set; }
        public int? CategoryId { get; set; }
        public string? SubcategoryName { get; set; }
        public string? SubcategoryImagePath { get; set; }
        public bool? Flag { get; set; }
    }
}
