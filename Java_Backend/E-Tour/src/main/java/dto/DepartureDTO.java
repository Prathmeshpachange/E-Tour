package dto;


import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartureDTO {
    private Integer departureId;
    private LocalDate departureDate;
    private LocalDate endDate;
    private Integer noOfDays;
    
    private Integer packageId;  
}
