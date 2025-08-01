package dto;

import entity.PaxType;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PassengerDTO {
    private Integer paxId;
    private String paxName;
    private LocalDate paxBirthdate;
    private PaxType paxType;
    private Double paxAmount;
    private Integer bookingId;
}
