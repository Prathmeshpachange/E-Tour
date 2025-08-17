using System;
using System.Collections.Generic;

namespace SafarnamaApplication.Models;

public partial class Cost
{
    public int CostId { get; set; }

    public int? PackageId { get; set; }

    public double? SinglePersonCost { get; set; }

    public double? ExtraPersonCost { get; set; }

    public double? ChildWithBed { get; set; }

    public double? ChildWithoutBed { get; set; }

    public DateOnly? ValidFrom { get; set; }

    public DateOnly? ValidTo { get; set; }

    public virtual Package? Package { get; set; }
}
