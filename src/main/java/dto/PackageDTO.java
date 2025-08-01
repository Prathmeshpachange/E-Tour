package dto;

import java.time.LocalDate;
import java.util.List;

import entity.Cost;
import entity.Departure;
import entity.Itinerary;
import entity.Booking;
import lombok.*;
import entity.Subcategory;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PackageDTO {
    private Integer packageId;
    private String packageName;
    private String packageInfo;
    private String packageImagePath;
    private Integer durationDays;
    private LocalDate startDate;
    private LocalDate endDate;

    private Integer subcategoryId;

//    private List<Itinerary> itineraries;
    private List<ItineraryDTO> itineraries; 


    private List<CostDTO> costs;
    private List<DepartureDTO> departures;
    
}
