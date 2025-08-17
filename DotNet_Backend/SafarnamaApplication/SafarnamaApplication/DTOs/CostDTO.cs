namespace SafarnamaApplication.DTOs
{
    public class CostDTO
    {
        public int CostId { get; set; }
        public int? PackageId { get; set; }
        public double? SinglePersonCost { get; set; }
        public double? ExtraPersonCost { get; set; }
        public double? ChildWithBed { get; set; }
        public double? ChildWithoutBed { get; set; }
        public DateOnly? ValidFrom { get; set; }
        public DateOnly? ValidTo { get; set; }
    }
}
