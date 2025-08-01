package mapper;

import java.util.stream.Collectors;

import dto.BookingDTO;
import dto.CategoryDTO;
import dto.CostDTO;
import dto.CustomerDTO;
import dto.DepartureDTO;
import dto.ItineraryDTO;
import dto.PackageDTO;
import dto.PassengerDTO;
import dto.SubcategoryDTO;
import entity.Booking;
import entity.Category;
import entity.Cost;
import entity.Customer;
import entity.Departure;
import entity.Itinerary;
import entity.Passenger;
import entity.Subcategory;
import entity.TourPackage;

public class Mapper {

    public static CategoryDTO MaptoCategoryDTO(Category category) {
        if (category == null) return null;

        CategoryDTO dto = new CategoryDTO();
        dto.setCategoryId(category.getCategoryId());
        dto.setCategoryCode(category.getCategoryCode());
        dto.setCategoryName(category.getCategoryName());
        dto.setCategoryImagePath(category.getCategoryImagePath());
        dto.setFlag(category.getFlag());

        return dto;
    }

    public static Category MaptoCategory(CategoryDTO dto) {
        if (dto == null) return null;

        Category category = new Category();
        category.setCategoryId(dto.getCategoryId());
        category.setCategoryCode(dto.getCategoryCode());
        category.setCategoryName(dto.getCategoryName());
        category.setCategoryImagePath(dto.getCategoryImagePath());
        category.setFlag(dto.getFlag());

        return category;
    }
    
    public static PackageDTO MapToPackageDTO(TourPackage pkg) {
        if (pkg == null) return null;

        PackageDTO dto = new PackageDTO();
        dto.setPackageId(pkg.getPackageId());
        dto.setPackageName(pkg.getPackageName());
        dto.setPackageInfo(pkg.getPackageInfo());
        dto.setPackageImagePath(pkg.getPackageImagePath());
        dto.setDurationDays(pkg.getDurationDays());
        dto.setStartDate(pkg.getStartDate());
        dto.setEndDate(pkg.getEndDate());
       
        dto.setSubcategoryId(pkg.getSubcategory() != null ? pkg.getSubcategory().getSubcategoryId() : null);
        if (pkg.getItineraries() != null) {
            dto.setItineraries(pkg.getItineraries().stream()
                .map(Mapper::mapToItineraryDTO)
                .collect(Collectors.toList()));
        }
        if (pkg.getCosts() != null) {
            dto.setCosts(pkg.getCosts().stream()
                .map(Mapper::mapToCostDTO)
                .collect(Collectors.toList()));
        }
//        dto.setDepartures(null);
        if (pkg.getDepartures() != null) {
            dto.setDepartures(pkg.getDepartures().stream()
                .map(Mapper::mapToDepartureDTO)
                .collect(Collectors.toList()));
        }

        

        return dto;
    }

    
    public static TourPackage MapToPackage(PackageDTO dto, Subcategory subcategory) {
        if (dto == null) return null;

        TourPackage pkg = new TourPackage();
        pkg.setPackageId(dto.getPackageId());
        pkg.setPackageName(dto.getPackageName());
        pkg.setPackageInfo(dto.getPackageInfo());
        pkg.setPackageImagePath(dto.getPackageImagePath());
        pkg.setDurationDays(dto.getDurationDays());
        pkg.setStartDate(dto.getStartDate());
        pkg.setEndDate(dto.getEndDate());
        pkg.setSubcategory(subcategory);
        if (dto.getItineraries() != null) {
            pkg.setItineraries(dto.getItineraries().stream()
                .map(itineraryDTO -> {
                    Itinerary itinerary = Mapper.mapToItinerary(itineraryDTO);
                    itinerary.setTourPackage(pkg);
                    return itinerary;
                }).collect(Collectors.toList()));
        }
        if (dto.getDepartures() != null) {
            pkg.setDepartures(dto.getDepartures().stream()
                .map(departureDTO -> {
                	Departure departure = Mapper.mapToDeparture(departureDTO, pkg); 
                    departure.setTourPackage(pkg);
                    return departure;
                }).collect(Collectors.toList()));
        }
        
        

        return pkg;
    }
    
    
    
    public static ItineraryDTO mapToItineraryDTO(Itinerary itinerary) {
        if (itinerary == null) return null;
        ItineraryDTO dto = new ItineraryDTO();
        dto.setItineraryId(itinerary.getItineraryId());
        dto.setDayNo(itinerary.getDayNo());
        dto.setDayDetail(itinerary.getDayDetail());
        return dto;
    }

    public static Itinerary mapToItinerary(ItineraryDTO dto) {
        if (dto == null) return null;
        Itinerary itinerary = new Itinerary();
        itinerary.setItineraryId(dto.getItineraryId());
        itinerary.setDayNo(dto.getDayNo());
        itinerary.setDayDetail(dto.getDayDetail());
        return itinerary;
    }


    public static SubcategoryDTO MaptoSubcategoryDto(Subcategory subcat) {
		SubcategoryDTO subDto = new SubcategoryDTO();
		subDto.setSubcategoryId(subcat.getSubcategoryId());
		subDto.setSubcategoryName(subcat.getSubcategoryName());
		subDto.setSubcategoryImagePath(subcat.getSubcategoryImagePath());
		
		return subDto;
	}
	public static Subcategory MaptoSubcategory(SubcategoryDTO subcatDto) {
		Subcategory subcat = new Subcategory();
		subcat.setSubcategoryId(subcatDto.getSubcategoryId());
		subcat.setSubcategoryName(subcatDto.getSubcategoryName());
		subcat.setSubcategoryImagePath(subcatDto.getSubcategoryImagePath());
		subcat.setFlag(subcatDto.getFlag());
		return subcat;
	}

	
	  // ✅ Itinerary Mappings
    public static ItineraryDTO MapToItineraryDTO(Itinerary itinerary) {
        if (itinerary == null) return null;

        ItineraryDTO dto = new ItineraryDTO();
        dto.setItineraryId(itinerary.getItineraryId());
        dto.setDayNo(itinerary.getDayNo());
        dto.setDayDetail(itinerary.getDayDetail());
        dto.setPackageId(itinerary.getTourPackage() != null ? itinerary.getTourPackage().getPackageId() : null);

        return dto;
    }

    public static Itinerary MapToItinerary(ItineraryDTO dto, TourPackage tourPackage) {
        if (dto == null) return null;

        Itinerary itinerary = new Itinerary();
        itinerary.setItineraryId(dto.getItineraryId());
        itinerary.setDayNo(dto.getDayNo());
        itinerary.setDayDetail(dto.getDayDetail());
        itinerary.setTourPackage(tourPackage);

        return itinerary;
    }
    
    
    // ✅ Customer Mappings
    public static CustomerDTO MapToCustomerDTO(Customer customer) {
        if (customer == null) return null;

        CustomerDTO dto = new CustomerDTO();
        dto.setCustId(customer.getCustId());
        dto.setCustName(customer.getCustName());
        dto.setCustEmail(customer.getCustEmail());
        dto.setCustPhone(customer.getCustPhone());
        dto.setCustDob(customer.getCustDob());
        dto.setCustGender(customer.getCustGender());
        dto.setCustAddress(customer.getCustAddress());
        dto.setCustCity(customer.getCustCity());
        dto.setCustState(customer.getCustState());
        dto.setCustPincode(customer.getCustPincode());
        dto.setCustCountry(customer.getCustCountry());
        dto.setCustCreatedAt(customer.getCustCreatedAt());

        return dto;
    }

    public static Customer MapToCustomer(CustomerDTO dto) {
        if (dto == null) return null;

        Customer customer = new Customer();
        customer.setCustId(dto.getCustId());
        customer.setCustName(dto.getCustName());
        customer.setCustEmail(dto.getCustEmail());
        customer.setCustPhone(dto.getCustPhone());
        customer.setCustDob(dto.getCustDob());
        customer.setCustGender(dto.getCustGender());
        customer.setCustAddress(dto.getCustAddress());
        customer.setCustCity(dto.getCustCity());
        customer.setCustState(dto.getCustState());
        customer.setCustPincode(dto.getCustPincode());
        customer.setCustCountry(dto.getCustCountry());
        customer.setCustCreatedAt(dto.getCustCreatedAt());

        return customer;
    }
    
    
    
    //passenger
    
    public static PassengerDTO MapToPassengerDTO(Passenger passenger) {
        if (passenger == null) return null;

        PassengerDTO dto = new PassengerDTO();
        dto.setPaxId(passenger.getPaxId());
        dto.setPaxName(passenger.getPaxName());
        dto.setPaxBirthdate(passenger.getPaxBirthdate());
        dto.setPaxType(passenger.getPaxType());
        dto.setPaxAmount(passenger.getPaxAmount());
        dto.setBookingId(passenger.getBooking() != null ? passenger.getBooking().getBookingId() : null);

        return dto;
    }

    public static Passenger MapToPassenger(PassengerDTO dto, Booking booking) {
        if (dto == null) return null;

        Passenger passenger = new Passenger();
        passenger.setPaxId(dto.getPaxId());
        passenger.setPaxName(dto.getPaxName());
        passenger.setPaxBirthdate(dto.getPaxBirthdate());
        passenger.setPaxType(dto.getPaxType());
        passenger.setPaxAmount(dto.getPaxAmount());
        passenger.setBooking(booking);

        return passenger;
    }
    
    
    //Booking
    
 // ✅ Booking DTO to Entity
    public static Booking MapToBooking(BookingDTO bookingDTO) {
        if (bookingDTO == null) return null;

        Booking booking = new Booking();
        booking.setBookingId(bookingDTO.getBookingId());
        booking.setBookingDate(bookingDTO.getBookingDate());
        booking.setNoOfPax(bookingDTO.getNoOfPax());
        booking.setTourAmount(bookingDTO.getTourAmount());
        booking.setTaxes(bookingDTO.getTaxes());
        booking.setTotalAmount(bookingDTO.getTotalAmount());
        booking.setPaymentStatus(bookingDTO.getPaymentStatus());

        if (bookingDTO.getCustomerId() != null) {
            Customer customer = new Customer();
            customer.setCustId(bookingDTO.getCustomerId());
            booking.setCustomer(customer);
        }

        if (bookingDTO.getPackageId() != null) {
            TourPackage tourPackage = new TourPackage();
            tourPackage.setPackageId(bookingDTO.getPackageId());
            booking.setTourPackage(tourPackage);
        }

        if (bookingDTO.getDepartureId() != null) {
            Departure departure = new Departure();
            departure.setDepartureId(bookingDTO.getDepartureId());
            booking.setDeparture(departure);
        }

        return booking;
    }

    // ✅ Booking Entity to DTO
    public static BookingDTO MapToBookingDTO(Booking booking) {
        if (booking == null) return null;

        BookingDTO dto = new BookingDTO();
        dto.setBookingId(booking.getBookingId());
        dto.setBookingDate(booking.getBookingDate());
        dto.setNoOfPax(booking.getNoOfPax());
        dto.setTourAmount(booking.getTourAmount());
        dto.setTaxes(booking.getTaxes());
        dto.setTotalAmount(booking.getTotalAmount());
        dto.setPaymentStatus(booking.getPaymentStatus());

        if (booking.getCustomer() != null)
            dto.setCustomerId(booking.getCustomer().getCustId());

        if (booking.getTourPackage() != null)
            dto.setPackageId(booking.getTourPackage().getPackageId());

        if (booking.getDeparture() != null)
            dto.setDepartureId(booking.getDeparture().getDepartureId());

        return dto;
    }

    public static CostDTO mapToCostDTO(Cost cost) {
        if (cost == null) return null;

        CostDTO dto = new CostDTO();
        dto.setCostId(cost.getCostId());
        dto.setSinglePersonCost(cost.getSinglePersonCost());
        dto.setExtraPersonCost(cost.getExtraPersonCost());
        dto.setChildWithBed(cost.getChildWithBed());
        dto.setChildWithoutBed(cost.getChildWithoutBed());
        dto.setValidFrom(cost.getValidFrom());
        dto.setValidTo(cost.getValidTo());
        return dto;
    }

    public static Cost mapToCost(CostDTO dto) {
        if (dto == null) return null;

        Cost cost = new Cost();
        cost.setCostId(dto.getCostId());
        cost.setSinglePersonCost(dto.getSinglePersonCost());
        cost.setExtraPersonCost(dto.getExtraPersonCost());
        cost.setChildWithBed(dto.getChildWithBed());
        cost.setChildWithoutBed(dto.getChildWithoutBed());
        cost.setValidFrom(dto.getValidFrom());
        cost.setValidTo(dto.getValidTo());
        return cost;
    }
    
    
    public static DepartureDTO mapToDepartureDTO(Departure departure) {
        if (departure == null) return null;

        DepartureDTO dto = new DepartureDTO();
        dto.setDepartureId(departure.getDepartureId());
        dto.setDepartureDate(departure.getDepartDate());
        dto.setEndDate(departure.getEndDate());
        dto.setNoOfDays(departure.getNoOfDays());
        
        if (departure.getTourPackage() != null) {
            dto.setPackageId(departure.getTourPackage().getPackageId());
        }
        return dto;
    }
    
    public static Departure mapToDeparture(DepartureDTO dto, TourPackage pkg) {
        if (dto == null) return null;

        Departure departure = new Departure();
        departure.setDepartureId(dto.getDepartureId());
        departure.setDepartDate(dto.getDepartureDate());
        departure.setEndDate(dto.getEndDate());
        departure.setNoOfDays(dto.getNoOfDays());
        departure.setTourPackage(pkg);

        return departure;
    }


}
