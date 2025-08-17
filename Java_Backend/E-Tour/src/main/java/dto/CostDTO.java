package dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CostDTO {
    private Integer costId;
    private Double singlePersonCost;
    private Double extraPersonCost;
    private Double childWithBed;
    private Double childWithoutBed;
    private LocalDate validFrom;
    private LocalDate validTo;

    
}
