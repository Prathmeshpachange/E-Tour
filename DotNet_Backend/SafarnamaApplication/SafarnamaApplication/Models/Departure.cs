using System;
using System.Collections.Generic;

namespace SafarnamaApplication.Models;

public partial class Departure
{
    public int DepartureId { get; set; }

    public int? PackageId { get; set; }

    public DateOnly? DepartDate { get; set; }

    public DateOnly? EndDate { get; set; }

    public int? NoOfDays { get; set; }

    public virtual ICollection<Booking> Bookings { get; set; } = new List<Booking>();

    public virtual Package? Package { get; set; }
}
