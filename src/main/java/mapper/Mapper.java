package mapper;

import java.util.Arrays;
import java.util.stream.Collectors;

import dto.*;
import entity.*;

/**
 * Common Mapper class for converting between Entity and DTO objects.
 * Grouped by module: Category, Subcategory, Package, Itinerary, etc.
 */
public class Mapper {

    /* ------------------------- CATEGORY MAPPERS ------------------------- */

    // Convert Entity -> DTO
    public static CategoryDTO MaptoCategoryDTO(Category category) {
        if (category == null) return null;

        CategoryDTO dto = new CategoryDTO();
        dto.setCategoryId(category.getCategoryId());
        dto.setCategoryCode(category.getCategoryCode());
        dto.setCategoryName(category.getCategoryName());
        dto.setCategoryImagePath(category.getCategoryImagePath());
        dto.setFlag(category.getFlag());

        // Map subcategories of category if present
        if (category.getSubcategories() != null) {
            dto.setSubcategories(
                category.getSubcategories().stream()
                    .map(Mapper::MaptoSubcategoryDto)
                    .collect(Collectors.toList())
            );
        }
        return dto;
    }

    // Convert DTO -> Entity
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

    /* ------------------------- SUBCATEGORY MAPPERS ------------------------- */

    // Convert Entity -> DTO
    public static SubcategoryDTO MaptoSubcategoryDto(Subcategory subcat) {
        SubcategoryDTO dto = new SubcategoryDTO();
        dto.setSubcategoryId(subcat.getSubcategoryId());
        dto.setSubcategoryName(subcat.getSubcategoryName());
        dto.setSubcategoryImagePath(subcat.getSubcategoryImagePath());
        dto.setFlag(subcat.getFlag());
        dto.setCategoryId(subcat.getCategory() != null ? subcat.getCategory().getCategoryId() : null);
        return dto;
    }

    // Convert DTO -> Entity
    public static Subcategory MaptoSubcategory(SubcategoryDTO dto) {
        Subcategory subcat = new Subcategory();
        subcat.setSubcategoryId(dto.getSubcategoryId());
        subcat.setSubcategoryName(dto.getSubcategoryName());
        subcat.setSubcategoryImagePath(dto.getSubcategoryImagePath());
        subcat.setFlag(dto.getFlag());

        if (dto.getCategoryId() != null) {
            Category cat = new Category();
            cat.setCategoryId(dto.getCategoryId());
            subcat.setCategory(cat);
        }
        return subcat;
    }

    /* ------------------------- PACKAGE MAPPERS ------------------------- */

    // Convert Entity -> DTO
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

        // Map itineraries
        if (pkg.getItineraries() != null) {
            dto.setItineraries(pkg.getItineraries().stream()
                .map(Mapper::mapToItineraryDTO)
                .collect(Collectors.toList()));
        }

        // Map costs
        if (pkg.getCosts() != null) {
            dto.setCosts(pkg.getCosts().stream()
                .map(Mapper::mapToCostDTO)
                .collect(Collectors.toList()));
        }

        // Map departures
        if (pkg.getDepartures() != null) {
            dto.setDepartures(pkg.getDepartures().stream()
                .map(Mapper::mapToDepartureDTO)
                .collect(Collectors.toList()));
        }

        return dto;
    }

    // Convert DTO -> Entity
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

        // Set itineraries
        if (dto.getItineraries() != null) {
            pkg.setItineraries(dto.getItineraries().stream()
                .map(itineraryDTO -> {
                    Itinerary itinerary = Mapper.mapToItinerary(itineraryDTO);
                    itinerary.setTourPackage(pkg);
                    return itinerary;
                }).collect(Collectors.toList()));
        }

        // Set departures
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

    /* ------------------------- ITINERARY MAPPERS ------------------------- */

    // Convert Entity -> DTO
    public static ItineraryDTO mapToItineraryDTO(Itinerary itinerary) {
        if (itinerary == null) return null;

        ItineraryDTO dto = new ItineraryDTO();
        dto.setItineraryId(itinerary.getItineraryId());
        dto.setDayNo(itinerary.getDayNo());
        dto.setDayDetail(itinerary.getDayDetail());
        if (itinerary.getTourPackage() != null) {
            dto.setPackageId(itinerary.getTourPackage().getPackageId());
        }
        return dto;
    }

    // Convert DTO -> Entity
    public static Itinerary mapToItinerary(ItineraryDTO dto) {
        if (dto == null) return null;

        Itinerary itinerary = new Itinerary();
        itinerary.setItineraryId(dto.getItineraryId());
        itinerary.setDayNo(dto.getDayNo());
        itinerary.setDayDetail(dto.getDayDetail());

        if (dto.getPackageId() != null) {
            TourPackage pkg = new TourPackage();
            pkg.setPackageId(dto.getPackageId());
            itinerary.setTourPackage(pkg);
        }

        return itinerary;
    }

    /* ------------------------- CUSTOMER MAPPERS ------------------------- */

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

    /* ------------------------- PASSENGER MAPPERS ------------------------- */

    public static PassengerDTO mapToPassengerDTO(Passenger passenger) {
        if (passenger == null) return null;

        PassengerDTO dto = new PassengerDTO();
        dto.setPaxId(passenger.getPaxId());
        dto.setPaxName(passenger.getPaxName());
        dto.setPaxBirthdate(passenger.getPaxBirthdate());
        dto.setPaxAmount(passenger.getPaxAmount());

        if (passenger.getBooking() != null) {
            dto.setBookingId(passenger.getBooking().getBookingId());
        }

        if (passenger.getPaxType() != null) {
            dto.setPaxTypeId(passenger.getPaxType().ordinal());
            dto.setPaxTypeName(passenger.getPaxType().getDisplayName());
        }

        return dto;
    }

    public static Passenger mapToPassenger(PassengerDTO dto, Booking booking) {
        if (dto == null) return null;

        Passenger passenger = new Passenger();
        passenger.setPaxId(dto.getPaxId());
        passenger.setPaxName(dto.getPaxName());
        passenger.setPaxBirthdate(dto.getPaxBirthdate());
        passenger.setPaxAmount(dto.getPaxAmount());
        passenger.setBooking(booking);

        if (dto.getPaxTypeName() != null && !dto.getPaxTypeName().isEmpty()) {
            try {
                PaxType paxType = Arrays.stream(PaxType.values())
                        .filter(p -> p.getDisplayName().equalsIgnoreCase(dto.getPaxTypeName()))
                        .findFirst().orElse(null);
                passenger.setPaxType(paxType);
            } catch (Exception e) {
                passenger.setPaxType(null);
            }
        }

        return passenger;
    }

    /* ------------------------- BOOKING MAPPERS ------------------------- */

    public static Booking MapToBooking(BookingDTO dto) {
        if (dto == null) return null;

        Booking booking = new Booking();
        booking.setBookingId(dto.getBookingId());
        booking.setBookingDate(dto.getBookingDate());
        booking.setNoOfPax(dto.getNoOfPax());
        booking.setTourAmount(dto.getTourAmount());
        booking.setTaxes(dto.getTaxes());
        booking.setTotalAmount(dto.getTotalAmount());
        booking.setPaymentStatus(dto.getPaymentStatus());

        if (dto.getCustomerId() != null) {
            Customer customer = new Customer();
            customer.setCustId(dto.getCustomerId());
            booking.setCustomer(customer);
        }

        if (dto.getPackageId() != null) {
            TourPackage pkg = new TourPackage();
            pkg.setPackageId(dto.getPackageId());
            booking.setTourPackage(pkg);
        }

        if (dto.getDepartureId() != null) {
            Departure departure = new Departure();
            departure.setDepartureId(dto.getDepartureId());
            booking.setDeparture(departure);
        }

        return booking;
    }

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

        if (booking.getPassengers() != null) {
            dto.setPassengers(booking.getPassengers().stream()
                .map(Mapper::mapToPassengerDTO)
                .collect(Collectors.toList()));
        }

        return dto;
    }

    /* ------------------------- COST MAPPERS ------------------------- */

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

    /* ------------------------- DEPARTURE MAPPERS ------------------------- */

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
