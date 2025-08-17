using System.Diagnostics.Eventing.Reader;
using SafarnamaApplication.Models;

namespace SafarnamaApplication.DTOs
{
    public class PassengerDTO
    {
        public int PaxId { get; set; }
        public string? PaxName { get; set; }
        public DateOnly? PaxBirthdate { get; set; }
        public int? PaxTypeId { get; set; }
        public string? PaxTypeName { get; set; }
        public double? PaxAmount { get; set; }
        public int? BookingId { get; set; }

        public PaxType? PaxType { get; set; }
    }
}
