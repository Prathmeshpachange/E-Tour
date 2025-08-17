using System;
using System.Collections.Generic;

namespace SafarnamaApplication.Models;

public partial class Subcategory
{
    public int SubcategoryId { get; set; }

    public string SubcategoryCode { get; set; } = null!;

    public int? CategoryId { get; set; }

    public string? SubcategoryName { get; set; }

    public string? SubcategoryImagePath { get; set; }

    public bool? Flag { get; set; }

    public virtual Category? Category { get; set; }

    public virtual ICollection<Package> Packages { get; set; } = new List<Package>();
}
