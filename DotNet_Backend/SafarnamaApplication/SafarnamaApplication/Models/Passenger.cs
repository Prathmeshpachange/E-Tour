using System;
using System.Collections.Generic;

namespace SafarnamaApplication.Models;

public partial class Passenger
{
    public int PaxId { get; set; }

    public int? BookingId { get; set; }

    public string? PaxName { get; set; }

    public DateOnly? PaxBirthdate { get; set; }

    public int? PaxType { get; set; }

    public double? PaxAmount { get; set; }

    public virtual Booking? Booking { get; set; }
}
