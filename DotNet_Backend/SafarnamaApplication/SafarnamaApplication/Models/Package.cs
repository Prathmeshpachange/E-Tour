using System;
using System.Collections.Generic;

namespace SafarnamaApplication.Models;

public partial class Package
{
    public int PackageId { get; set; }

    public int? SubcategoryId { get; set; }

    public string? PackageName { get; set; }

    public string? PackageInfo { get; set; }

    public string? PackageImagePath { get; set; }

    public int? DurationDays { get; set; }

    public DateOnly? StartDate { get; set; }

    public DateOnly? EndDate { get; set; }

    public int? CategoryId { get; set; }

    public virtual ICollection<Booking> Bookings { get; set; } = new List<Booking>();

    public virtual Category? Category { get; set; }

    public virtual ICollection<Cost> Costs { get; set; } = new List<Cost>();

    public virtual ICollection<Departure> Departures { get; set; } = new List<Departure>();

    public virtual ICollection<Itinerary> Itineraries { get; set; } = new List<Itinerary>();

    public virtual Subcategory? Subcategory { get; set; }
}
