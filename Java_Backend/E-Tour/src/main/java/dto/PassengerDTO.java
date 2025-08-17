package dto;

import lombok.*;
import java.time.LocalDate;
import entity.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PassengerDTO {
    private Integer paxId;
    private String paxName;
    private LocalDate paxBirthdate;
    private Integer paxTypeId;     
    private String paxTypeName;     
    private Double paxAmount;
    private Integer bookingId;
    private PaxType paxType;
}
