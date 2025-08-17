using System;
using System.Collections.Generic;
using Microsoft.EntityFrameworkCore;
using Pomelo.EntityFrameworkCore.MySql.Scaffolding.Internal;

namespace SafarnamaApplication.Models;

public partial class SafarnamadbContext : DbContext
{
    public SafarnamadbContext()
    {
    }

    public SafarnamadbContext(DbContextOptions<SafarnamadbContext> options)
        : base(options)
    {
    }

    public virtual DbSet<Authentication> Authentications { get; set; }

    public virtual DbSet<Booking> Bookings { get; set; }

    public virtual DbSet<Category> Categories { get; set; }

    public virtual DbSet<Cost> Costs { get; set; }

    public virtual DbSet<Customer> Customers { get; set; }

    public virtual DbSet<Departure> Departures { get; set; }

    public virtual DbSet<Itinerary> Itineraries { get; set; }

    public virtual DbSet<Package> Packages { get; set; }

    public virtual DbSet<Passenger> Passengers { get; set; }

    public virtual DbSet<Subcategory> Subcategories { get; set; }

    protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
#warning To protect potentially sensitive information in your connection string, you should move it out of source code. You can avoid scaffolding the connection string by using the Name= syntax to read it from configuration - see https://go.microsoft.com/fwlink/?linkid=2131148. For more guidance on storing connection strings, see https://go.microsoft.com/fwlink/?LinkId=723263.
        => optionsBuilder.UseMySql("server=localhost;port=3306;database=safarnamadb;user=root;password=tiger", Microsoft.EntityFrameworkCore.ServerVersion.Parse("8.0.36-mysql"));

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        modelBuilder
            .UseCollation("utf8mb4_0900_ai_ci")
            .HasCharSet("utf8mb4");

        modelBuilder.Entity<Authentication>(entity =>
        {
            entity.HasKey(e => e.AuthId).HasName("PRIMARY");

            entity.ToTable("authentication");

            entity.HasIndex(e => e.CustId, "cust_id");

            entity.Property(e => e.AuthId).HasColumnName("auth_id");
            entity.Property(e => e.CustId).HasColumnName("cust_id");
            entity.Property(e => e.LastLogin)
                .HasColumnType("datetime")
                .HasColumnName("last_login");
            entity.Property(e => e.Password)
                .HasMaxLength(255)
                .HasColumnName("password");
            entity.Property(e => e.Status)
                .HasDefaultValueSql("'1'")
                .HasColumnName("status");

            entity.HasOne(d => d.Cust).WithMany(p => p.Authentications)
                .HasForeignKey(d => d.CustId)
                .HasConstraintName("authentication_ibfk_1");
        });

        modelBuilder.Entity<Booking>(entity =>
        {
            entity.HasKey(e => e.BookingId).HasName("PRIMARY");

            entity.ToTable("booking");

            entity.HasIndex(e => e.CustId, "cust_id");

            entity.HasIndex(e => e.DepartureId, "departure_id");

            entity.HasIndex(e => e.PackageId, "package_id");

            entity.Property(e => e.BookingId).HasColumnName("booking_id");
            entity.Property(e => e.BookingDate)
                .HasColumnType("datetime")
                .HasColumnName("booking_date");
            entity.Property(e => e.CustId).HasColumnName("cust_id");
            entity.Property(e => e.DepartureId).HasColumnName("departure_id");
            entity.Property(e => e.NoOfPax).HasColumnName("no_of_pax");
            entity.Property(e => e.PackageId).HasColumnName("package_id");
            entity.Property(e => e.PaymentStatus)
                .HasMaxLength(255)
                .HasColumnName("payment_status");
            entity.Property(e => e.Taxes).HasColumnName("taxes");
            entity.Property(e => e.TotalAmount).HasColumnName("total_amount");
            entity.Property(e => e.TourAmount).HasColumnName("tour_amount");

            entity.HasOne(d => d.Cust).WithMany(p => p.Bookings)
                .HasForeignKey(d => d.CustId)
                .HasConstraintName("booking_ibfk_1");

            entity.HasOne(d => d.Departure).WithMany(p => p.Bookings)
                .HasForeignKey(d => d.DepartureId)
                .HasConstraintName("booking_ibfk_3");

            entity.HasOne(d => d.Package).WithMany(p => p.Bookings)
                .HasForeignKey(d => d.PackageId)
                .HasConstraintName("booking_ibfk_2");
        });

        modelBuilder.Entity<Category>(entity =>
        {
            entity.HasKey(e => e.CategoryId).HasName("PRIMARY");

            entity.ToTable("category");

            entity.Property(e => e.CategoryId).HasColumnName("category_id");
            entity.Property(e => e.CategoryCode)
                .HasMaxLength(255)
                .HasColumnName("category_code");
            entity.Property(e => e.CategoryImagePath)
                .HasMaxLength(255)
                .HasColumnName("category_image_path");
            entity.Property(e => e.CategoryName)
                .HasMaxLength(255)
                .HasColumnName("category_name");
            entity.Property(e => e.Flag)
                .HasDefaultValueSql("'0'")
                .HasColumnName("flag");
        });

        modelBuilder.Entity<Cost>(entity =>
        {
            entity.HasKey(e => e.CostId).HasName("PRIMARY");

            entity.ToTable("cost");

            entity.HasIndex(e => e.PackageId, "package_id");

            entity.Property(e => e.CostId).HasColumnName("cost_id");
            entity.Property(e => e.ChildWithBed).HasColumnName("child_with_bed");
            entity.Property(e => e.ChildWithoutBed).HasColumnName("child_without_bed");
            entity.Property(e => e.ExtraPersonCost).HasColumnName("extra_person_cost");
            entity.Property(e => e.PackageId).HasColumnName("package_id");
            entity.Property(e => e.SinglePersonCost).HasColumnName("single_person_cost");
            entity.Property(e => e.ValidFrom).HasColumnName("valid_from");
            entity.Property(e => e.ValidTo).HasColumnName("valid_to");

            entity.HasOne(d => d.Package).WithMany(p => p.Costs)
                .HasForeignKey(d => d.PackageId)
                .HasConstraintName("cost_ibfk_1");
        });

        modelBuilder.Entity<Customer>(entity =>
        {
            entity.HasKey(e => e.CustId).HasName("PRIMARY");

            entity.ToTable("customer");

            entity.HasIndex(e => e.CustEmail, "cust_email").IsUnique();

            entity.Property(e => e.CustId).HasColumnName("cust_id");
            entity.Property(e => e.CustAddress)
                .HasMaxLength(255)
                .HasColumnName("cust_address");
            entity.Property(e => e.CustCity)
                .HasMaxLength(255)
                .HasColumnName("cust_city");
            entity.Property(e => e.CustCountry)
                .HasMaxLength(255)
                .HasColumnName("cust_country");
            entity.Property(e => e.CustCreatedAt)
                .HasDefaultValueSql("CURRENT_TIMESTAMP")
                .HasColumnType("datetime")
                .HasColumnName("cust_created_at");
            entity.Property(e => e.CustDob).HasColumnName("cust_dob");
            entity.Property(e => e.CustEmail).HasColumnName("cust_email");
            entity.Property(e => e.CustGender)
                .HasMaxLength(1)
                .IsFixedLength()
                .HasColumnName("cust_gender");
            entity.Property(e => e.CustName)
                .HasMaxLength(255)
                .HasColumnName("cust_name");
            entity.Property(e => e.CustPhone)
                .HasMaxLength(255)
                .HasColumnName("cust_phone");
            entity.Property(e => e.CustPincode)
                .HasMaxLength(255)
                .HasColumnName("cust_pincode");
            entity.Property(e => e.CustState)
                .HasMaxLength(255)
                .HasColumnName("cust_state");
        });

        modelBuilder.Entity<Departure>(entity =>
        {
            entity.HasKey(e => e.DepartureId).HasName("PRIMARY");

            entity.ToTable("departure");

            entity.HasIndex(e => e.PackageId, "package_id");

            entity.Property(e => e.DepartureId).HasColumnName("departure_id");
            entity.Property(e => e.DepartDate).HasColumnName("depart_date");
            entity.Property(e => e.EndDate).HasColumnName("end_date");
            entity.Property(e => e.NoOfDays).HasColumnName("no_of_days");
            entity.Property(e => e.PackageId).HasColumnName("package_id");

            entity.HasOne(d => d.Package).WithMany(p => p.Departures)
                .HasForeignKey(d => d.PackageId)
                .HasConstraintName("departure_ibfk_1");
        });

        modelBuilder.Entity<Itinerary>(entity =>
        {
            entity.HasKey(e => e.ItineraryId).HasName("PRIMARY");

            entity.ToTable("itinerary");

            entity.HasIndex(e => e.PackageId, "package_id");

            entity.Property(e => e.ItineraryId).HasColumnName("itinerary_id");
            entity.Property(e => e.DayDetail)
                .HasMaxLength(255)
                .HasColumnName("day_detail");
            entity.Property(e => e.DayNo).HasColumnName("day_no");
            entity.Property(e => e.PackageId).HasColumnName("package_id");

            entity.HasOne(d => d.Package).WithMany(p => p.Itineraries)
                .HasForeignKey(d => d.PackageId)
                .HasConstraintName("itinerary_ibfk_1");
        });

        modelBuilder.Entity<Package>(entity =>
        {
            entity.HasKey(e => e.PackageId).HasName("PRIMARY");

            entity.ToTable("package");

            entity.HasIndex(e => e.CategoryId, "FKcqtvgyjqbydbrtu3230gfkp5t");

            entity.HasIndex(e => e.SubcategoryId, "subcategory_id");

            entity.Property(e => e.PackageId).HasColumnName("package_id");
            entity.Property(e => e.CategoryId).HasColumnName("category_id");
            entity.Property(e => e.DurationDays).HasColumnName("duration_days");
            entity.Property(e => e.EndDate).HasColumnName("end_date");
            entity.Property(e => e.PackageImagePath)
                .HasMaxLength(255)
                .HasColumnName("package_image_path");
            entity.Property(e => e.PackageInfo)
                .HasMaxLength(255)
                .HasColumnName("package_info");
            entity.Property(e => e.PackageName)
                .HasMaxLength(255)
                .HasColumnName("package_name");
            entity.Property(e => e.StartDate).HasColumnName("start_date");
            entity.Property(e => e.SubcategoryId).HasColumnName("subcategory_id");

            entity.HasOne(d => d.Category).WithMany(p => p.Packages)
                .HasForeignKey(d => d.CategoryId)
                .HasConstraintName("FKcqtvgyjqbydbrtu3230gfkp5t");

            entity.HasOne(d => d.Subcategory).WithMany(p => p.Packages)
                .HasForeignKey(d => d.SubcategoryId)
                .HasConstraintName("package_ibfk_1");
        });

        modelBuilder.Entity<Passenger>(entity =>
        {
            entity.HasKey(e => e.PaxId).HasName("PRIMARY");

            entity.ToTable("passenger");

            entity.HasIndex(e => e.BookingId, "booking_id");

            entity.Property(e => e.PaxId).HasColumnName("pax_id");
            entity.Property(e => e.BookingId).HasColumnName("booking_id");
            entity.Property(e => e.PaxAmount).HasColumnName("pax_amount");
            entity.Property(e => e.PaxBirthdate).HasColumnName("pax_birthdate");
            entity.Property(e => e.PaxName)
                .HasMaxLength(255)
                .HasColumnName("pax_name");
            entity.Property(e => e.PaxType)
                .HasColumnType("enum('TWIN_SHARING','SINGLE_PERSON','EXTRA_PERSON','CHILD_WITH_BED')")
                .HasColumnName("pax_type");

            entity.HasOne(d => d.Booking).WithMany(p => p.Passengers)
                .HasForeignKey(d => d.BookingId)
                .HasConstraintName("passenger_ibfk_1");
        });

        modelBuilder.Entity<Subcategory>(entity =>
        {
            entity.HasKey(e => e.SubcategoryId).HasName("PRIMARY");

            entity.ToTable("subcategory");

            entity.HasIndex(e => e.CategoryId, "category_id");

            entity.Property(e => e.SubcategoryId).HasColumnName("subcategory_id");
            entity.Property(e => e.CategoryId).HasColumnName("category_id");
            entity.Property(e => e.Flag)
                .HasDefaultValueSql("'0'")
                .HasColumnName("flag");
            entity.Property(e => e.SubcategoryCode)
                .HasMaxLength(3)
                .IsFixedLength()
                .HasColumnName("subcategory_code");
            entity.Property(e => e.SubcategoryImagePath)
                .HasMaxLength(255)
                .HasColumnName("subcategory_image_path");
            entity.Property(e => e.SubcategoryName)
                .HasMaxLength(255)
                .HasColumnName("subcategory_name");

            entity.HasOne(d => d.Category).WithMany(p => p.Subcategories)
                .HasForeignKey(d => d.CategoryId)
                .HasConstraintName("subcategory_ibfk_1");
        });

        OnModelCreatingPartial(modelBuilder);
    }

    partial void OnModelCreatingPartial(ModelBuilder modelBuilder);
}
