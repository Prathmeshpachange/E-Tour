using System;
using System.Collections.Generic;

namespace SafarnamaApplication.Models;

public partial class Booking
{
    public int BookingId { get; set; }

    public DateTime? BookingDate { get; set; }

    public int? CustId { get; set; }

    public int? PackageId { get; set; }

    public int? DepartureId { get; set; }

    public int? NoOfPax { get; set; }

    public double? TourAmount { get; set; }

    public double? Taxes { get; set; }

    public double? TotalAmount { get; set; }

    public string? PaymentStatus { get; set; }

    public virtual Customer? Cust { get; set; }

    public virtual Departure? Departure { get; set; }

    public virtual Package? Package { get; set; }

    public virtual ICollection<Passenger> Passengers { get; set; } = new List<Passenger>();
}
