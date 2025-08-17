using System;
using System.Collections.Generic;

namespace SafarnamaApplication.Models;

public partial class Itinerary
{
    public int ItineraryId { get; set; }

    public int? PackageId { get; set; }

    public int? DayNo { get; set; }

    public string? DayDetail { get; set; }

    public virtual Package? Package { get; set; }
}
