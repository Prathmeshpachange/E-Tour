using System;
using System.Collections.Generic;

namespace SafarnamaApplication.Models;

public partial class Authentication
{
    public int AuthId { get; set; }

    public int? CustId { get; set; }

    public string? Password { get; set; }

    public DateTime? LastLogin { get; set; }

    public sbyte? Status { get; set; }

    public virtual Customer? Cust { get; set; }
}
