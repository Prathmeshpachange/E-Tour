using AutoMapper;
using SafarnamaApplication.DTOs;
using SafarnamaApplication.Models;

namespace SafarnamaApplication.Profiles
{
    public class MappingProfile : Profile
    {
        public MappingProfile()
        {
            // ===== CATEGORY MAPPING =====
            CreateMap<Category, CategoryDTO>()
                .ForMember(dest => dest.Flag, opt => opt.MapFrom(src => src.Flag ?? false));

            CreateMap<CategoryDTO, Category>()
                .ForMember(dest => dest.Flag, opt => opt.MapFrom(src => src.Flag));

            // ===== SUBCATEGORY MAPPING =====
            CreateMap<Subcategory, SubcategoryDTO>()
                .ForMember(dest => dest.Flag, opt => opt.MapFrom(src => src.Flag ?? false));

            CreateMap<SubcategoryDTO, Subcategory>()
                .ForMember(dest => dest.Flag, opt => opt.MapFrom(src => src.Flag));

            // ===== COST MAPPINGS =====
            CreateMap<Cost, CostDTO>().ReverseMap();

            // ===== ITINERARY MAPPINGS =====
            CreateMap<Itinerary, ItineraryDTO>().ReverseMap();

            // ===== DEPARTURE MAPPINGS =====
            CreateMap<Departure, DepartureDTO>().ReverseMap();

            // ===== PACKAGE MAPPING =====
            CreateMap<Package, PackageDTO>()
                .ForMember(dest => dest.CategoryName,
                    opt => opt.MapFrom(src => src.Category != null ? src.Category.CategoryName : null))
                .ForMember(dest => dest.SubcategoryName,
                    opt => opt.MapFrom(src => src.Subcategory != null ? src.Subcategory.SubcategoryName : null))
                .ForMember(dest => dest.Costs, opt => opt.MapFrom(src => src.Costs))
                .ForMember(dest => dest.Itineraries, opt => opt.MapFrom(src => src.Itineraries))
                .ForMember(dest => dest.Departures, opt => opt.MapFrom(src => src.Departures))
                .ReverseMap();

            CreateMap<Cost, CostDTO>().ReverseMap();

            // ===== CUSTOMER MAPPING =====
            CreateMap<Customer, CustomerDTO>().ReverseMap();

            // ===== BOOKING MAPPING =====
            // Explicitly map foreign keys and passengers like Spring Boot mapper
            CreateMap<BookingDTO, Booking>()
                .ForMember(dest => dest.CustId, opt => opt.MapFrom(src => src.CustomerId))
                .ForMember(dest => dest.PackageId, opt => opt.MapFrom(src => src.PackageId))
                .ForMember(dest => dest.DepartureId, opt => opt.MapFrom(src => src.DepartureId))
                .ForMember(dest => dest.Passengers, opt => opt.MapFrom(src => src.Passengers));

            CreateMap<Booking, BookingDTO>()
                .ForMember(dest => dest.CustomerId, opt => opt.MapFrom(src => src.CustId))
                .ForMember(dest => dest.PackageId, opt => opt.MapFrom(src => src.PackageId))
                .ForMember(dest => dest.DepartureId, opt => opt.MapFrom(src => src.DepartureId))
                .ForMember(dest => dest.Passengers, opt => opt.MapFrom(src => src.Passengers));

            // ===== PASSENGER MAPPING =====
            CreateMap<Passenger, PassengerDTO>()
                .ForMember(dest => dest.PaxTypeId, opt => opt.MapFrom(src => (int)src.PaxType))
                .ForMember(dest => dest.PaxTypeName, opt => opt.MapFrom(src => src.PaxType.ToString())); // Gives TWIN_SHARING, etc.

            CreateMap<PassengerDTO, Passenger>()
                .ForMember(dest => dest.PaxType, opt => opt.MapFrom(src => src.PaxType))
                .ForMember(dest => dest.BookingId, opt => opt.MapFrom(src => src.BookingId));
        }
    }
}
