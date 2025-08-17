using System;
using System.Collections.Generic;

namespace SafarnamaApplication.Models;

public partial class Category
{
    public int CategoryId { get; set; }

    public string? CategoryCode { get; set; }

    public string? CategoryName { get; set; }

    public string? CategoryImagePath { get; set; }

    public bool? Flag { get; set; }

    public virtual ICollection<Package> Packages { get; set; } = new List<Package>();

    public virtual ICollection<Subcategory> Subcategories { get; set; } = new List<Subcategory>();
}
