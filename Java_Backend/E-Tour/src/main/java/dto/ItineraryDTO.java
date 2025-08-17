package dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItineraryDTO {
    private Integer itineraryId;
    private Integer dayNo;
    private String dayDetail;
    private Integer packageId;
}
